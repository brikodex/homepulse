# =========================
# Stage 1: Build Angular
# =========================
FROM node:20-alpine AS build

WORKDIR /app

# Copier package.json et package-lock.json pour installer dépendances
COPY package*.json ./

# Installer les dépendances de manière propre
RUN npm ci

# Copier tout le code source
COPY . .

# Build Angular pour production
RUN npm run build --configuration=production

# =========================
# Stage 2: Serve avec Nginx
# =========================
FROM nginx:alpine

# Copier la configuration Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copier les fichiers buildés depuis le stage 1
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

# Exposer le port HTTP
EXPOSE 80

# Commande pour démarrer Nginx en premier plan
CMD ["nginx", "-g", "daemon off;"]
