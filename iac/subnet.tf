resource "aws_subnet" "private-us-east-1a" {
  vpc_id            = aws_vpc.pk_vpc.id
  cidr_block        = "10.0.0.0/20" #range de ips
  availability_zone = "us-east-1a"
  
  tags = {
      "Name"                                            = "private-us-east-1a"
      "kubernetes.io/role/internal-elb"                 = "1"
      "kubernetes.io/cluster/${var.pk_cluster_name}"    = "owned"
  }
}

resource "aws_subnet" "public-us-east-1a" {
    vpc_id                  = aws_vpc.pk_vpc.id
    cidr_block              = "10.0.32.0/20"
    availability_zone       = "us-east-1a"
    map_public_ip_on_launch = true

    tags = {
        "Name"                                          = "public-us-east-1a"
        "kubernetes.io/role/elb"                        = "1"
        "kubernetes.io/cluster/${var.pk_cluster_name}"  = "owned"
    }
}

resource "aws_subnet" "private-us-east-1b" {
    vpc_id              = aws_vpc.pk_vpc.id
    cidr_block          = "10.0.16.0/20" #range de ips
    availability_zone   = "us-east-1b"
    
    tags = {
        "Name"                                          = "private-us-east-1b"
        "kubernetes.io/role/internal-elb"               = "1"
        "kubernetes.io/cluster/${var.pk_cluster_name}"  = "owned"
    }
}

resource "aws_subnet" "public-us-east-1b" {
    vpc_id                  = aws_vpc.pk_vpc.id
    cidr_block              = "10.0.48.0/20"
    availability_zone       = "us-east-1b"
    map_public_ip_on_launch = true
    
    tags = {
        "Name"                                          = "public-us-east-1b"
        "kubernetes.io/role/elb"                        = "1"
        "kubernetes.io/cluster/${var.pk_cluster_name}"  = "owned"
    }
}