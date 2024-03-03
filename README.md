# sample-payment-gateway
Sample Payment Gateway is an app that accepts credit card payments for processing and maintains a transaction history.

## Build Setup

1. Check out this repository.

2. Add ".env" file and set environmental variables in it.

3. Create folder "tmpvol" in the project's root.

3. Install Docker.

## Docker

### Start the system

```bash
docker compose up -d
```

### Shutdown the system

```bash
docker compose down
```

### Rebuild an individual service

```bash
docker compose build backend
```

### Check the latest build date of a service

```bash
docker inspect -f '{{.Created}}' backend
```

### Redeploy an individual service

```bash
docker compose up --no-deps -d backend
```

### Connect to logs of Spring Boot backend

```bash
docker logs --tail 50 --follow --timestamps backend
```

### push to Docker Hub

```bash
docker tag spg-backend iiplabs/spg-backend:0.0.1
docker push iiplabs/spg-backend:0.0.1
```

## Testing

Create test.json file with the transaction payload

### Add payment transaction to the system

```bash
curl -d @test.json -H "Content-Type: application/json" http://localhost:9091/api/v1/payments
```

Example:
{
  "invoice": "1234567",
  "amount": 1299,
  "currency": "EUR",
  "cardholder": {
    "name": "First Last",
    "email": "test@domain.com"
  },
  "card": {
    "pan": "4024007197526238",
    "expiry": "0624",
    "cvv": "789"
  }
}

Example of system response if the payload is accepted:
{"approved":true}

Example of system response when payload has not passed data validation:
{"errors":{"email":"Invalid cardholder email format."},"approved":false}

Example of system response for system issues like database not available or the invoice # is not unique:
{"timestamp":"2021-05-20T20:44:44.695+00:00","status":500,"error":"Internal Server Error","message":"","path":"/api/v1/payments"}

### Fetch an existing transaction from the system

```bash
curl http://localhost:9091/api/v1/payments/1234567
```

The system shall respond with JSON message, with some of the attributes masked per PCI requirements:

[{"invoice":"1234567","amount":1299,"currency":"EUR","card":{"pan":"************6238","expiry":"****"},"cardholder":{"name":"**********","email":"test@domain.com"}}]

## Audit

The system shall write the sanitized transaction record to the log file "/tmp/audit.json"

<b>Note:</b> the file is not a correct JSON array, but a collection of JSON objects instead.
