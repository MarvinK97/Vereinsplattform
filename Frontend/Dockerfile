FROM nginx:latest

RUN rm -rf /usr/share/nginx/html/*
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY dist/Frontend /usr/share/nginx/html
