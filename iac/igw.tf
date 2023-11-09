resource "aws_internet_gateway" "pk_igw" {
  vpc_id = aws_vpc.pk_vpc.id
  
  tags = {
      Name = "pk_igw"
  }
}