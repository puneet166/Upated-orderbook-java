package com.gitbitex.openapi.controller;

import com.gitbitex.marketdata.entity.Product;
import com.gitbitex.marketdata.entity.User;
import com.gitbitex.marketdata.manager.AccountManager;
import com.gitbitex.marketdata.manager.UserManager;
import com.gitbitex.marketdata.repository.ProductRepository;
import com.gitbitex.matchingengine.command.CancelOrderCommand;
import com.gitbitex.matchingengine.command.DepositCommand;
import com.gitbitex.matchingengine.command.MatchingEngineCommandProducer;
import com.gitbitex.matchingengine.command.PutProductCommand;
import com.gitbitex.marketdata.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * For demonstration, do not expose to external users ！！！！！！
 * For demonstration, do not expose to external users ！！！！！！
 * For demonstration, do not expose to external users ！！！！！！
 */
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final MatchingEngineCommandProducer producer;
    private final AccountManager accountManager;
    private final ProductRepository productRepository;
    private final UserManager userManager;
    private final UserRepository userRepository;


  @PostMapping("/api/admin/createUser")
public User createUser(@RequestBody @Valid PostUserRequest request) {
    // System.out.println("Creating 48 in admin controller--------: " + request.id);

    User userDetails = userRepository.findByUserId(request.getId());
    // System.out.print("-------------",request.getId());
    // System.out.println("Creating 52 in admin controller--------: " + userDetails);

    if (userDetails != null) {
        return userDetails;
    }
    System.out.println("Creating 57 in admin controller--------: " + userDetails);

    return userManager.createUser(request.getId());
}

 @PostMapping("/api/admin/deposit")
public String deposit(
   @RequestBody @Valid PostDepositFundRequest request,
    @RequestAttribute(required = false) User currentUser
) {
    if (currentUser == null) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    User user = userRepository.findByUserId(currentUser.getId());
    if (!user.getRole()) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This is not an admin");
    }

    String userId = request.getId();
    String currency = request.currency;
    String amount = request.amount;

    if (userId == null || currency == null || amount == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameters");
    }

    User userDetails = userRepository.findByUserId(userId);
    if (userDetails == null) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This user is not a registered user");
    }

    DepositCommand command = new DepositCommand();
    command.setUserId(userId);
    command.setCurrency(currency);
    command.setAmount(new BigDecimal(amount));
    command.setTransactionId(UUID.randomUUID().toString());
    producer.send(command, null);

    return "ok";
}



    @PutMapping("/api/admin/products")
    public Product saveProduct(@RequestBody @Valid PutProductRequest request,@RequestAttribute(required = false) User currentUser) {
         if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        User user = userRepository.findByUserId(currentUser.getId());
        if (!user.getRole()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This is not admin");
        }
        String productId = request.getBaseCurrency() + "-" + request.getQuoteCurrency();
        Product product = new Product();
        product.setId(productId);
        product.setBaseCurrency(request.baseCurrency);
        product.setQuoteCurrency(request.quoteCurrency);
        product.setBaseScale(6);
        product.setQuoteScale(2);
        product.setBaseMinSize(BigDecimal.ZERO);
        product.setBaseMaxSize(new BigDecimal("100000000"));
        product.setQuoteMinSize(BigDecimal.ZERO);
        product.setQuoteMaxSize(new BigDecimal("10000000000"));
        productRepository.save(product);

        PutProductCommand putProductCommand = new PutProductCommand();
        putProductCommand.setProductId(product.getId());
        putProductCommand.setBaseCurrency(product.getBaseCurrency());
        putProductCommand.setQuoteCurrency(product.getQuoteCurrency());
        producer.send(putProductCommand, null);

        return product;
    }

    public void cancelOrder(String orderId, String productId) {
        CancelOrderCommand command = new CancelOrderCommand();
        command.setProductId(productId);
        command.setOrderId(orderId);
        producer.send(command, null);
    }

    @Getter
    @Setter
    public static class PutProductRequest {
        @NotBlank
        private String baseCurrency;
        @NotBlank
        private String quoteCurrency;

    }

    @Getter
    @Setter
    public static class PostUserRequest {
        @NotBlank
        private String id;
       
    }
    @Getter
    @Setter
    public static class PostDepositFundRequest {
        @NotBlank
        private String id;
        private String currency;
        private String amount;
       
    }

}
