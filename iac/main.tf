provider "aws" {
  region = var.pk_region
}

terraform {
    required_providers {
        aws = {
            source = "hashicorp/aws"
            version = "~> 4.0" //todo: ver a versao a atual
        }
    }
}