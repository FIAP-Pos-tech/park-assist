provider "aws" {
  region = "sa-east-1" # Modifique conforme sua região
}

resource "aws_vpc" "eks_vpc" {
  cidr_block = "10.0.0.0/16"
  enable_dns_support = true
  enable_dns_hostnames = true
  tags = {
    Name = "eks-vpc"
  }
}

resource "aws_subnet" "eks_subnet_public_1" {
  vpc_id     = aws_vpc.eks_vpc.id
  cidr_block = "10.0.1.0/24"
  availability_zone = "sa-east-1" # Modifique conforme sua região e zona de disponibilidade
  map_public_ip_on_launch = true
  tags = {
    Name = "eks-subnet-public-1"
  }
}

resource "aws_subnet" "eks_subnet_public_2" {
  vpc_id     = aws_vpc.eks_vpc.id
  cidr_block = "10.0.2.0/24"
  availability_zone = "sa-east-1" # Modifique conforme sua região e zona de disponibilidade
  map_public_ip_on_launch = true
  tags = {
    Name = "eks-subnet-public-2"
  }
}

resource "aws_subnet" "eks_subnet_private_1" {
  vpc_id     = aws_vpc.eks_vpc.id
  cidr_block = "10.0.3.0/24"
  availability_zone = "sa-east-1" # Modifique conforme sua região e zona de disponibilidade
  tags = {
    Name = "eks-subnet-private-1"
  }
}

resource "aws_subnet" "eks_subnet_private_2" {
  vpc_id     = aws_vpc.eks_vpc.id
  cidr_block = "10.0.4.0/24"
  availability_zone = "sa-east-1" # Modifique conforme sua região e zona de disponibilidade
  tags = {
    Name = "eks-subnet-private-2"
  }
}

resource "aws_security_group" "eks_default_sg" {
  vpc_id = aws_vpc.eks_vpc.id
  tags = {
    Name = "eks-default-sg"
  }
}

resource "aws_internet_gateway" "eks_igw" {
  vpc_id = aws_vpc.eks_vpc.id
}

resource "aws_eip" "nat_eip" {
}

resource "aws_nat_gateway" "eks_nat_gw" {
  subnet_id     = aws_subnet.eks_subnet_public_1.id
  allocation_id = aws_eip.nat_eip.id
}


# ... Adicione mais recursos conforme necessário (por exemplo, Internet Gateway, NAT Gateway, etc.)
