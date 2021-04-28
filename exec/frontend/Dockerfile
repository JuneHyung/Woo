FROM node:lts-alpine as build-stage
WORKDIR /fridge
COPY package*.json ./

RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable-alpine as production-stage
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx/nginx.conf /etc/nginx/conf.d/nginx.conf

COPY --from=build-stage ./fridge/dist /usr/share/nginx/html/ubn
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]