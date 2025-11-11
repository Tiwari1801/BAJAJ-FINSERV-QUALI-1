package BAJAJ.FINSERV;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BajajFinservHealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BajajFinservHealthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner runOnStartup(RestTemplate restTemplate) {
        return args -> {
            String generateWebhookUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("name", "Aditya Tiwari");
            requestBody.put("regNo", "PES1UG22CS047");
            requestBody.put("email", "adityatiwari5670@gmail.edu");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.exchange(generateWebhookUrl, HttpMethod.POST, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String webhookUrl = (String) response.getBody().get("webhook");
                String accessToken = (String) response.getBody().get("accessToken");

                System.out.println("Webhook URL: " + webhookUrl);
                System.out.println("Access Token: " + accessToken);

                String regNo = requestBody.get("regNo");
                String digitsOnly = regNo.replaceAll("\\D+", "");
                String lastTwoDigitsStr = digitsOnly.substring(digitsOnly.length() - 2);
                int lastTwoDigits = Integer.parseInt(lastTwoDigitsStr);

                String finalSQLQuery = (lastTwoDigits % 2 == 0)
                        ? "SELECT department_id, AVG(salary) FROM employees GROUP BY department_id;"
                        : "SELECT department, COUNT(*) FROM employees GROUP BY department;";

                headers.clear();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(accessToken);

                Map<String, String> answerBody = new HashMap<>();
                answerBody.put("finalQuery", finalSQLQuery);
                HttpEntity<Map<String, String>> answerEntity = new HttpEntity<>(answerBody, headers);

                try {
                    ResponseEntity<String> answerResponse = restTemplate.exchange(
                            webhookUrl, HttpMethod.POST, answerEntity, String.class);
                    System.out.println("Response: " + answerResponse.getStatusCode() + " -> " + answerResponse.getBody());
                } catch (HttpClientErrorException e) {
                    System.out.println("Failed with status " + e.getStatusCode());
                    System.out.println("Response body: " + e.getResponseBodyAsString());
                }
            } else {
                System.out.println("Webhook generation failed. Status: " + response.getStatusCode());
            }
        };
    }
}
