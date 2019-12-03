### STAGE 1: Build ###
FROM node as builder

COPY package.json package-lock.json ./

RUN npm ci && mkdir /ng-app && mv ./node_modules ./ng-app

WORKDIR /ng-app

COPY ./ ./

## Build the angular app in production mode and store the artifacts in dist folder
RUN npm install -g @angular/cli@8.2.0
RUN ng build --prod --output-path=dist

### STAGE 2: Setup ###
FROM nginx:1.17.6-alpine

ENV API_URL=http://localhost:8080/customers-api/v1/customers

## Copy our default nginx config
COPY nginx/default.conf /etc/nginx/conf.d/

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

## From ‘builder’ stage copy over the artifacts in dist folder to default nginx public folder
COPY --from=builder /ng-app/dist /usr/share/nginx/html