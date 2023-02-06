//resource "kubernetes_namespace" "mongo-namespace" {
//  metadata {
//    name = "mongo-namespace"
//  }
//}

resource "kubernetes_persistent_volume_claim" "mongo-pvc" {
  metadata {
//    namespace = kubernetes_namespace.mongo-namespace.metadata[0].name
    name = "mongo-pvc"
  }

  spec {
    access_modes = ["ReadWriteOnce"]

    resources {
      requests = {
        storage = "256Mi"
      }
    }
  }
}

resource "kubernetes_deployment" "mongo-deployment" {
  metadata {
//    namespace = kubernetes_namespace.mongo-namespace.metadata[0].name
    name = "mongo"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "mongo"
      }
    }

    template {
      metadata {
        name = "mongo"
        labels = {
          app = "mongo"
        }
      }

      spec {
        container {
          image = "mongo:4.4"
          name  = "mongo"

          port {
            container_port = 27017
          }

          volume_mount {
            mount_path = "/data/db"
            name = "storage"
          }
        }

        volume {
          name = "storage"

          persistent_volume_claim {
            claim_name = "mongo-pvc"
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "mongo-service" {
  metadata {
//    namespace = kubernetes_namespace.mongo-namespace.metadata[0].name
    name = "mongo"
  }

  spec {
    selector = {
      app = "mongo"
    }

    port {
      port        = 27017
      target_port = 27017
    }
  }
}
