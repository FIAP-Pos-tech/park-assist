variable "pk_region" {
  default = "us-east-1"
}

variable "pk_cluster_name" {
  default = "park-assist-cluster"
}

variable "pk_cluster_version" {
    default = "1.28"
}

variable "nm_kube_system" {
  default = "kube-system"
}

variable "pk_vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
  default     = "10.0.0.0/16"
}