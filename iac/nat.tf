resource "aws_eip" "pk_nat" {
  vpc = true
  
  tags = {
      Name = "nat"
  }
}

resource "aws_nat_gateway" "pk_nat" {
  allocation_id = aws_eip.pk_nat.id
  subnet_id = aws_subnet.public-us-east-1a.id
  
  tags = {
      Name = "nat"
  }
  
  depends_on = [ aws_internet_gateway.pk_igw ]
}