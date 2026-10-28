FROM node:20.6.0-alpine3.21 AS build

WORKDIR /workspace

COPY package.json package-lock.json ./
RUN npm ci

COPY . .
RUN npm run build

FROM nginx:1.27.4-alpine

COPY --from=build /workspace/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80