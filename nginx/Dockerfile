# =========================
# Nginx Gateway (Reverse Proxy)
# =========================
FROM nginx:stable-alpine

# Install openssl for self-signed cert generation
RUN apk add --no-cache openssl

# Create directory for certs
RUN mkdir -p /etc/nginx/certs

# Generate self-signed certificate (valid for 365 days)
# In production, you should mount real certificates via volumes
RUN openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
    -keyout /etc/nginx/certs/privkey.pem \
    -out /etc/nginx/certs/fullchain.pem \
    -subj "/C=FR/ST=France/L=Paris/O=HomePulse/CN=localhost"

# Copy custom configuration
COPY nginx.conf /etc/nginx/nginx.conf

# Expose ports
EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]
