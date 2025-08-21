# --- ESTÁGIO 1: Build (Construção) ---
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# --- ESTÁGIO 2: Serve (Servidor de Produção) ---
FROM nginx:stable-alpine
COPY --from=build /app/dist /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d
EXPOSE 5173
CMD ["nginx", "-g", "daemon off;"]