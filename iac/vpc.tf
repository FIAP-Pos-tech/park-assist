resource "aws_vpc" "pk_vpc" {
    cidr_block = var.pk_vpc_cidr
    
    enable_dns_support = true
    enable_dns_hostnames = true
    
    tags = {
        Name = "pk_vpc"
    }
}
