resource "aws_route_table" "eks_public_rt" {
  vpc_id = aws_vpc.eks_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.eks_igw.id
  }
}

resource "aws_route_table" "eks_private_rt" {
  vpc_id = aws_vpc.eks_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.eks_nat_gw.id
  }
}

resource "aws_route_table_association" "eks_public_rta_1" {
  subnet_id      = aws_subnet.eks_subnet_public_1.id
  route_table_id = aws_route_table.eks_public_rt.id
}

resource "aws_route_table_association" "eks_public_rta_2" {
  subnet_id      = aws_subnet.eks_subnet_public_2.id
  route_table_id = aws_route_table.eks_public_rt.id
}

resource "aws_route_table_association" "eks_private_rta_1" {
  subnet_id      = aws_subnet.eks_subnet_private_1.id
  route_table_id = aws_route_table.eks_private_rt.id
}

resource "aws_route_table_association" "eks_private_rta_2" {
  subnet_id      = aws_subnet.eks_subnet_private_2.id
  route_table_id = aws_route_table.eks_private_rt.id
}

resource "aws_security_group_rule" "allow_all_outbound" {
  security_group_id = aws_security_group.eks_default_sg.id
  type              = "egress"
  from_port         = 0
  to_port           = 0
  protocol          = "-1"
  cidr_blocks       = ["0.0.0.0/0"]
}
