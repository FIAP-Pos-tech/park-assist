resource "aws_route_table" "rt_private" {
  vpc_id = aws_vpc.pk_vpc.id
  
  route {
      cidr_block = "0.0.0.0/0"
      nat_gateway_id = aws_nat_gateway.pk_nat.id
  }
  
  tags = {
      Name = "rt_private"
  }
}

resource "aws_route_table" "rt_public" {
    vpc_id = aws_vpc.pk_vpc.id
    
    route {
        cidr_block = "0.0.0.0/0"
        gateway_id = aws_internet_gateway.pk_igw.id
    }
    
    tags = {
        Name = "rt_public"
    }
}

resource "aws_route_table_association" "private-us-east-1a" {
    subnet_id = aws_subnet.private-us-east-1a.id
    route_table_id = aws_route_table.rt_private.id
}

resource "aws_route_table_association" "private-us-east-1b" {
    subnet_id = aws_subnet.private-us-east-1b.id
    route_table_id = aws_route_table.rt_private.id
}

resource "aws_route_table_association" "public-us-east-1a" {
    subnet_id = aws_subnet.public-us-east-1a.id
    route_table_id = aws_route_table.rt_public.id
}

resource "aws_route_table_association" "public-us-east-1b" {
    subnet_id = aws_subnet.public-us-east-1b.id
    route_table_id = aws_route_table.rt_public.id
}