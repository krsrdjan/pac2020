//resource "kubernetes_namespace" "backend-namespace" {
//  metadata {
//    name = "backend-namespace"
//  }
//}

resource "kubernetes_deployment" "backend-deployment" {
  metadata {
//    namespace = kubernetes_namespace.backend-namespace.metadata[0].name
    name = "conference-app-api"
  }

  depends_on = [
    kubernetes_deployment.mongo-deployment,
    kubernetes_service.mongo-service
  ]

  spec {
    replicas = 1
    selector {
      match_labels = {
        app = "conference-app-api"
      }
    }

    template {
      metadata {
        labels = {
          app = "conference-app-api"
        }
      }

      spec {
        termination_grace_period_seconds = 30
        container {
          name  = "conference-app-api"
          image = "krsrdjan/conference-app-api:0.0.1-SNAPSHOT"
          image_pull_policy = "Always"
          port {
            container_port = 8080
          }
          env {
            name = "MONGO_URL"
            value = "mongodb://mongo:27017/dev"
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "backend-service" {
  metadata {
//    namespace = kubernetes_namespace.backend-namespace.metadata[0].name
    name = "conference-app-api"
  }

  spec {
    selector = {
      app = "conference-app-api"
    }

    port {
      port        = 8080
      protocol = "TCP"
      target_port = 8080
    }
  }
}
