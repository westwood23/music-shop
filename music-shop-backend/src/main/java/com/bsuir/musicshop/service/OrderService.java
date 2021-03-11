package com.bsuir.musicshop.service;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsuir.musicshop.mapper.OrderMapper;
import com.bsuir.musicshop.model.*;
import com.bsuir.musicshop.repository.ItemRepository;
import com.bsuir.musicshop.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    private static final String EMAIL_TEMPLATE_HTML = "/templates/email-template.html";
    private static final String SERVICE_EMAIL = "zabela.kryscina@yandex.by";

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper mapper;
    private final JavaMailSender mailSender;

    public void addOrder(Order order) {

	setItems(order);

	order.setOrderDate(LocalDate.now());
	order.setOrderCost(calculateCost(order));
	order.setIsPaid(Boolean.FALSE);

	orderRepository.save(order);
    }

    public void updateOrder(Order order) {

	setItems(order);
	order.setOrderCost(calculateCost(order));

	orderRepository.save(order);
    }

    public void markIsPaid(Integer orderId) {
	Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
	if (order.getIsPaid() == Boolean.FALSE) {
	    order.setIsPaid(Boolean.TRUE);
	    orderRepository.save(order);
	    sendEmail(order.getOrderId());
	}

	createBill(orderId);
    }

    public void deleteOrder(Integer orderId) {
	orderRepository.deleteById(orderId);
    }

    public List<OrderDto> findAllOrderDTOs() {
	return orderRepository.findAll().stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

    public List<OrderDto> findSortedDtos() {
	return orderRepository.findAll().stream().sorted(Comparator.comparing(Order::getOrderCost).reversed())
		.map(mapper::mapToDto).collect(Collectors.toList());
    }

    public Order findOrderById(Integer orderId) {
	return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderDto> findOrdersByDates(LocalDate from, LocalDate to) {
	return orderRepository.findAllByOrderDateBetween(from, to).stream().map(mapper::mapToDto)
		.collect(Collectors.toList());
    }

    public Stats getStats() {
	Stats stats = new Stats();
	stats.setCount(getOrdersCount());
	stats.setCost(calculateOrdersCosts());
	return stats;
    }

    private Long getOrdersCount() {
	return orderRepository.count();
    }

    private BigDecimal calculateOrdersCosts() {
	return orderRepository.findAll().stream().map(Order::getOrderCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateCost(Order order) {
	return order.getItemsIds().stream().map(Integer::parseInt).map(itemRepository::findById)
		.flatMap(Optional::stream).map(Item::getItemPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void sendEmail(Integer orderNumber) {
	MimeMessage message = mailSender.createMimeMessage();

	try {
	    MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

	    String html = createStringFromTemplate(orderNumber.toString());

	    helper.setFrom(SERVICE_EMAIL);
	    helper.setTo("kryscinazabela@gmail.com");
	    helper.setSubject("Order paid");
	    helper.setText(html, true);
	} catch (UnknownFormatConversionException | MessagingException | IOException e) {
	    throw new RuntimeException();
	}

	mailSender.send(message);
    }

    private String createStringFromTemplate(String var) throws IOException {
	String html;

	try (InputStream inputStream = getClass().getResourceAsStream(EMAIL_TEMPLATE_HTML);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
	    html = reader.lines().collect(Collectors.joining(System.lineSeparator()));
	} catch (NullPointerException e) {
	    throw new RuntimeException("Template not found");
	}

	return String.format(html, var);
    }

    private void createBill(Integer id) {
	Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

	Bill bill = new Bill();

	bill.setNumber(order.getOrderId().toString());
	bill.setDate(order.getOrderDate().toString());
	bill.setCost(order.getOrderCost().toString());
	bill.setItemsCount(String.valueOf(order.getItemsList().size()));

	writeBill(bill);
    }

    private void writeBill(Bill bill) {
	try (FileWriter writer = new FileWriter("bill.txt", false)) {
	    String text = "Thank you for the purchase! \n" + "Order number: " + bill.getNumber()
		    + "\nOrder creation date: " + bill.getDate() + "\nItems count: " + bill.getItemsCount()
		    + "\nPayment date: "
		    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss")) + "\nCost: "
		    + bill.getCost();

	    writer.write(text);

	    writer.flush();
	} catch (IOException ex) {
	    System.err.print(ex.getMessage());
	}
    }

    private void setItems(Order order) {
	List<Item> items = order.getItemsIds().stream().map(Integer::parseInt).map(itemRepository::findById)
		.flatMap(Optional::stream).collect(Collectors.toList());

	order.setItemsList(items);
    }
}