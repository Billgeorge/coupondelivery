# Coupon Delivery System
This Component returns items for a coupon using the favorite list items and the coupon amount.

# Resources
/coupon
This is the unique and main resource of the component. It has the next operations:
* Get: Only a test endpoint, can be used for health check.
* Post: Determine the list of items that represent the nearest amount to the coupon amount.

# Endpoints
* http://localhost:8080/coupon

POST Request

`{
   "item_ids": ["MLA863086478", "MLA702949084", "MLA661497844", "MLA826692197", "MLA826692175","MLA826692175"],
   "amount": "5000.05"
   }`
 
 Post Response
 
 `{
      "items": [
          "MLA826692197",
          "MLA826692175",
          "MLA863086478",
          "MLA702949084"
      ],
      "amountToSpend": 4800.0
  }`
  
  # Run Local
  
  For building execute 
  
  `./mvnw clean package`
  
  For local running 
  
  `./mvnw spring-boot:run`
