resource "kubernetes_ingress" "backend-ingress" {
  metadata {
    //    namespace = kubernetes_namespace.backend-namespace.metadata[0].name
    name = "conference-app-ingress"
    annotations = {
      "nginx.ingress.kubernetes.io/rewrite-target" : "/$1"
    }
  }

  depends_on = [
    kubernetes_service.backend-service,
    kubernetes_deployment.backend-deployment,
    kubernetes_service.frontend-service,
    kubernetes_deployment.frontend-deployment
  ]

  spec {
    rule {
      http {
        path {
          path = "/?(.*)"
          backend {
            service_name = "frontend-app-client-service"
            service_port = "80"
          }
        }

        path {
          path = "/api/?(.*)"
          backend {
            service_name = "conference-app-api"
            service_port = "8080"
          }
        }
      }
    }

    rule {
      host = "backend.backend"
      http {
        path {
          path = "/"
          backend {
            service_name = "conference-app-api"
            service_port = "8080"
          }
        }
      }
    }
  }
}




