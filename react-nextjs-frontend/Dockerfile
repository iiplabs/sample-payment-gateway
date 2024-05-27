FROM node:21-alpine AS build
ARG BUILD_PROFILE
WORKDIR /app
COPY . /app

RUN apk update && apk upgrade
RUN apk add --no-cache libc6-compat

ENV NEXT_TELEMETRY_DISABLED 1

# npm ci requires package-lock.json present and up-to-date
RUN npm install

# when npm install won't install devDependencies when in production mode
ENV NODE_ENV $BUILD_PROFILE

RUN npm run build

FROM nginx:alpine
ARG BUILD_PROFILE
WORKDIR /usr/share/nginx/html
COPY --from=build /app/out/ ./