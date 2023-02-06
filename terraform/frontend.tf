//resource "kubernetes_namespace" "frontend-namespace" {
//  metadata {
//    name = "frontend-namespace"
//  }
//}

resource "kubernetes_deployment" "frontend-deployment" {
  metadata {
    //    namespace = kubernetes_namespace.frontend-namespace.metadata[0].name
    name = "frontend-app-client"
  }

  //  depends_on = [
  //    kubernetes_deployment.backend-deployment,
  //    kubernetes_service.backend-service]

  spec {
    replicas = 1
    selector {
      match_labels = {
        app = "frontend-app-client"
      }
    }

    template {
      metadata {
        labels = {
          app = "frontend-app-client"
        }
      }

      spec {
        termination_grace_period_seconds = 30
        container {
          name = "frontend-app-client"
          image = "krsrdjan/react-conference-web-app"
          image_pull_policy = "Always"
          port {
            container_port = 80
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "frontend-service" {
  metadata {
    //    namespace = kubernetes_namespace.frontend-namespace.metadata[0].name
    name = "frontend-app-client-service"
  }

  spec {
    selector = {
      app = "frontend-app-client"
    }

    port {
      port = 80
    }
  }
}