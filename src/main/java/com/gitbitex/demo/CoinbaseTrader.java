// package com.gitbitex.demo;

// import com.alibaba.fastjson.JSON;
// import com.gitbitex.AppProperties;
// import com.gitbitex.marketdata.entity.User;
// import com.gitbitex.openapi.controller.AdminController;
// import com.gitbitex.openapi.controller.AdminController.PutProductRequest;
// import com.gitbitex.openapi.controller.OrderController;
// import com.gitbitex.openapi.model.PlaceOrderRequest;
// import com.gitbitex.marketdata.manager.UserManager;
// import com.gitbitex.marketdata.entity.Product;
// import com.gitbitex.marketdata.repository.ProductRepository;
// import com.gitbitex.matchingengine.command.DepositCommand;

// import com.google.common.util.concurrent.RateLimiter;
// import lombok.Getter;
// import lombok.RequiredArgsConstructor;
// import lombok.Setter;
// import com.gitbitex.matchingengine.command.PutProductCommand;

// import lombok.extern.slf4j.Slf4j;
// import org.java_websocket.drafts.Draft_6455;
// import org.java_websocket.enums.ReadyState;
// import org.java_websocket.handshake.ServerHandshake;
// import org.springframework.stereotype.Component;
// import com.gitbitex.matchingengine.command.MatchingEngineCommandProducer;
// // import java.util.UUID;

// import javax.annotation.PostConstruct;
// import javax.annotation.PreDestroy;
// import java.net.URI;
// import java.net.URISyntaxException;
// import java.util.Random;
// import java.util.UUID;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;
// import java.math.BigDecimal;

// @Component
// @Slf4j
// @RequiredArgsConstructor
// public class CoinbaseTrader {
//     private static final RateLimiter rateLimiter = RateLimiter.create(10);
//     private final OrderController orderController;
//     private final ExecutorService executor = Executors.newFixedThreadPool(1);
//     private final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
//     private final AppProperties appProperties;
//     private final AdminController adminController;
//     private final UserManager userManager;
//     private final ProductRepository productRepository;
//     private final MatchingEngineCommandProducer producer;

//     @PostConstruct
//     public void init() throws URISyntaxException {

//         logger.info("start");
// // userManager.createUser("test@test.com", "12345678")
//         // User user = userManager.createUser("test@test.com", "12345678");
//         // PutProductRequest putProductRequest = new PutProductRequest();
//         // putProductRequest.setBaseCurrency("Taral");
//         // putProductRequest.setQuoteCurrency("USDT");
//         // // adminController.saveProduct(putProductRequest);
//         // //------------
//         // String productId = putProductRequest.getBaseCurrency() + "-" + putProductRequest.getQuoteCurrency();    
//         // Product product = new Product();
//         // product.setId(productId);
//         // product.setBaseCurrency("Taral");
//         // product.setQuoteCurrency("USDT");
//         // product.setBaseScale(6);
//         // product.setQuoteScale(2);
//         // product.setBaseMinSize(BigDecimal.ZERO);
//         // product.setBaseMaxSize(new BigDecimal("100000000"));
//         // product.setQuoteMinSize(BigDecimal.ZERO);
//         // product.setQuoteMaxSize(new BigDecimal("10000000000"));
//         // productRepository.save(product);

//         // PutProductCommand putProductCommand = new PutProductCommand();
//         // putProductCommand.setProductId(product.getId());
//         // putProductCommand.setBaseCurrency(product.getBaseCurrency());
//         // putProductCommand.setQuoteCurrency(product.getQuoteCurrency());
//         // producer.send(putProductCommand, null);

//         // //-------------
//         // //--------------

//         // DepositCommand command = new DepositCommand();
//         // command.setUserId(user.getId());
//         // command.setCurrency("Taral");
//         // command.setAmount(new BigDecimal("100000000000000"));
//         // command.setTransactionId(UUID.randomUUID().toString());
//         // producer.send(command, null);

//         // //)))))))))))))))
//         // //--------
//         // DepositCommand command_quote = new DepositCommand();
//         // command_quote.setUserId(user.getId());
//         // command_quote.setCurrency("USDT");
//         // command_quote.setAmount(new BigDecimal("100000000000000"));
//         // command_quote.setTransactionId(UUID.randomUUID().toString());
//         // producer.send(command_quote, null);
//         //,,,,,,,,,,,
//         // adminController.deposit(user.getId(), "Taral", "100000000000000");
//         // adminController.deposit(user.getId(), "USDT", "10000000000000");

//     //     MyClient client = new MyClient(new URI("wss://ws-feed.exchange.coinbase.com"));

//     //     scheduledExecutor.scheduleAtFixedRate(() -> {
//     //         try {
//     //             // test(user);
//     //             if (true) {
//     //                 return;
//     //             }

//     //             if (!client.isOpen()) {
//     //                 try {
//     //                     if (client.getReadyState().equals(ReadyState.NOT_YET_CONNECTED)) {
//     //                         logger.info("connecting...: {}", client.getURI());
//     //                         client.connectBlocking();
//     //                     } else if (client.getReadyState().equals(ReadyState.CLOSING) || client.getReadyState().equals(
//     //                             ReadyState.CLOSED)) {
//     //                         logger.info("reconnecting...: {}", client.getURI());
//     //                         client.reconnectBlocking();
//     //                     }
//     //                 } catch (Exception e) {
//     //                     logger.error("ws error ", e);
//     //                 }
//     //             } else {
//     //                 client.sendPing();
//     //             }
//     //         } catch (Exception e) {
//     //             logger.error("send ping error: {}", e.getMessage(), e);
//     //         }
//     //     }, 0, 3000, TimeUnit.MILLISECONDS);
//     // }

//     // @PreDestroy
//     // public void destroy() {
//     //     executor.shutdown();
//     //     scheduledExecutor.shutdown();
//     // }

//     // public void test(User user) {
//     //     PlaceOrderRequest order = new PlaceOrderRequest();
//     //     order.setProductId("Taral-USDT");
//     //     order.setClientOid(UUID.randomUUID().toString());
//     //     order.setPrice(String.valueOf(new Random().nextInt(10) + 1));
//     //     order.setSize(String.valueOf(new Random().nextInt(10) + 1));
//     //     order.setFunds(String.valueOf(new Random().nextInt(10) + 1));
//     //     order.setSide(new Random().nextBoolean() ? "BUY" : "SELL");
//     //     order.setType("limit");
//     //     orderController.placeOrder(order, user);
//     // }

//     @Getter
//     @Setter
//     public static class ChannelMessage {
//         private String type;
//         private String product_id;
//         private long tradeId;
//         private long sequence;
//         private String taker_order_id;
//         private String maker_order_id;
//         private String time;
//         private String size;
//         private String price;
//         private String side;
//         private String orderId;
//         private String remaining_size;
//         private String funds;
//         private String order_type;
//         private String reason;
//     }

//     public class MyClient extends org.java_websocket.client.WebSocketClient {
//         private final User user;

//         public MyClient(URI serverUri, User user) {
//             super(serverUri, new Draft_6455(), null, 1000);
//             this.user = user;
//         }

//         @Override
//         public void onOpen(ServerHandshake serverHandshake) {
//             logger.info("open");

//             send("{\"type\":\"subscribe\",\"product_ids\":[\"Taral-USD\"],\"channels\":[\"full\"],\"token\":\"\"}");
//         }

//         @Override
//         public void onMessage(String s) {
//             if (!rateLimiter.tryAcquire()) {
//                 return;
//             }
//             executor.execute(() -> {
//                 try {
//                     ChannelMessage message = JSON.parseObject(s, ChannelMessage.class);
//                     String productId = message.getProduct_id() + "T";
//                     switch (message.getType()) {
//                         case "received":
//                             //logger.info(JSON.toJSONString(message));
//                             if (message.getPrice() != null) {
//                                 PlaceOrderRequest order = new PlaceOrderRequest();
//                                 order.setProductId(productId);
//                                 order.setClientOid(UUID.randomUUID().toString());
//                                 order.setPrice(message.getPrice());
//                                 order.setSize(message.getSize());
//                                 order.setFunds(message.getFunds());
//                                 order.setSide(message.getSide().toLowerCase());
//                                 order.setType("limit");
//                                 orderController.placeOrder(order, user);
//                             }
//                             break;
//                         case "done":
//                             adminController.cancelOrder(message.getOrderId(), productId);
//                             break;
//                         default:
//                     }
//                 } catch (Exception e) {
//                     logger.error("error: {}", e.getMessage(), e);
//                 }
//             });
//         }

//         @Override
//         public void onClose(int i, String s, boolean b) {
//             logger.info("connection closed");
//         }

//         @Override
//         public void onError(Exception e) {
//             logger.error("error", e);
//         }
//     }
// }
