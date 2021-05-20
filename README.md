# sample-payment-gateway
Sample Payment Gateway is an app that accepts credit card payments for processing and maintains a transaction history.

## Build Setup

## Docker

```bash
docker compose up -d
```

```bash
docker compose down
```

```bash
docker logs --tail 50 --follow --timestamps spg-backend
```

## Testing

```bash
curl -d @test.json -H "Content-Type: application/json" http://localhost:9091/api/v1/payments
```

```bash
curl http://localhost:9091/api/v1/payments/1234567
```