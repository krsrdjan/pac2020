resource "kubernetes_deployment" "keycloak-deployment" {
  metadata {
    name = "keycloak"
    labels = {
      app = "keycloak"
    }
  }
  spec {
    replicas = 1
    selector {
      match_labels = {
        app = "keycloak"
      }
    }
    template {
      metadata {
        labels = {
          app = "keycloak"
        }
      }
      spec {
        container {
          name = "keycloak"
          image = "quay.io/keycloak/keycloak:12.0.1"
          port {
            name = "http"
            container_port = 8080
          }
          port {
            name = "https"
            container_port = 8443
          }
          readiness_probe {
            http_get {
              path = "/auth/realms/master"
              port = "8080"
            }
          }
          env {
            name = "KEYCLOAK_USER"
            value = "admin"
          }
          env {
            name = "KEYCLOAK_PASSWORD"
            value = "admin"
          }
          env {
            name = "PROXY_ADDRESS_FORWARDING"
            value = "true"
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "keycloak-service" {
  metadata {
    name = "keycloak"
    labels = {
      app = "keycloak"
    }
  }
  spec {
    selector = {
      app = "keycloak"
    }
    port {
      name = "http"
      port = 8080
      target_port = 8080
    }
    type = "LoadBalancer"
  }
}

resource "kubernetes_ingress" "keycloak-ingress" {
  metadata {
    name = "keycloak"
  }

  spec {
    tls {
      hosts = ["KEYCLOAK_HOST"]
    }
    rule {
      host = "KEYCLOAK_HOST"
      http {
        path {
          backend {
            service_name = "keycloak"
            service_port = "8080"
          }
        }
      }
    }
  }
}

