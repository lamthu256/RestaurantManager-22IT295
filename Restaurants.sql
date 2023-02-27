CREATE DATABASE RESTAURANTS;
USE RESTAURANTS;
CREATE TABLE NGUOIDUNG
(
    TenDangNhap VARCHAR(50) PRIMARY KEY,
    MatKhau CHAR(50),
    VaiTro NVARCHAR(50)
);
CREATE TABLE NHANVIEN
(
    MaNV NCHAR(10) PRIMARY KEY,
    HOTEN NVARCHAR(50),
    CHUCVU NVARCHAR(50)
);
CREATE TABLE BANAN
(
    MaBA NCHAR(10) PRIMARY KEY NOT NULL,
    TenBA NVARCHAR(50),
    TRANGTHAI NVARCHAR(50),
    SOCHONGOI INT
);
CREATE TABLE HOADON
(
    MaHD NCHAR(10) NOT NULL,
    MaMA CHAR(10) NOT NULL,
    TenMA NVARCHAR(50),
    SOLUONG INT NULL,
    DONGIA FLOAT NULL
);
CREATE TABLE DOANHTHU
(
    MaHD NCHAR(10) PRIMARY KEY NOT NULL,
    TenKH NVARCHAR(50),
    MaBA NCHAR(10),
    MaNV NCHAR(10),
    Ngay VARCHAR(20) NOT NULL,
    TONGTIEN FLOAT
);
CREATE TABLE MONAN
(
    MaMA NCHAR(10)PRIMARY KEY  NOT NULL,
    TenMA NVARCHAR(50),
    DONGIA FLOAT NULL
);
INSERT INTO NGUOIDUNG VALUES
('lamthu', 123456, N'Quản lí'); 

INSERT INTO NHANVIEN VALUES 
('NV1', N'Trần Văn Minh', N'Bảo vệ'),
('NV2', N'Nguyễn Thị T', N'Phục vụ'),
('NV3', N'Hà Minh H', N'Phục vụ'),
('NV4', N'Đặng Hồng X', N'Lao công'),
('NV5', N'Trương Văn V', N'Phục vụ'),
('NV6', N'Lê Lợi', N'Thu ngân'),
('NV7', N'Đoàn Viết Y', N'Phục vụ'),
('NV8', N'Huỳnh Lâm B', N'Lao công'),
('NV9', N'Thái Trần V', N'Đầu bếp'),
('NV10', N'Mai Thu N', N'Đầu bếp'),
('NV11', N'Đinh Trần G', N'Phục vụ');		

INSERT INTO BANAN VALUES
('B1', N'Đơn', N'Trống', 1),
('B2', N'Gia Đình', N'Có Người', 10),
('B3', N'Chưa cập nhập', N'Đôi', 8),
('B4', N'Chưa cập nhập', N'Trống', 14),
('B5', N'Chưa cập nhập', N'Trống', 1),
('B6', N'Chưa cập nhập', N'Có Người',15);

INSERT CHITIETHOADON VALUES
(1, 'V1', 100000, 1),
(1, 'V4', 435000, 1),
(1, 'V1', 100000, 2),
(2, 'V3', 265000, 2),
(2, 'V2', 270000, 3),
(2, 'V5', 150000, 1),
(2, 'V9', 135000, 4);

INSERT INTO HOADON VALUES
('1', 'Nguyễn Thị A', 'K3', 'NV1', null),
('2', 'Trần Văn B', 'K2', 'NV2', null),
('3', 'Trần Lâm Thư', 'K5', 'NV3', null),
('4', 'Nguyễn Viết C', 'K6', 'NV5', null),
('7', 'Hà Thị D', 'K8', 'NV8', null),
('5', 'Bùi Xuân E', 'K9', 'NV9', null),
('6', 'Lê Anh T ', 'K9', 'NV7', null);

INSERT INTO MONAN VALUES
('V1', N'Súp Hải Sản', 100000),
('V2', N'Gà Chiên Mắm', 270000),
('V3', N'Bò Xào Lăn', 265000),
('V4', N'Lẩu Thái', 435000),
('V5', N'Ba chỉ Nướng Vừng', 150000),
('V6', N'Gỏi Mực Thái', 80000),
('V7', N'Tôm Hùm', 500000),
('V8', N'Bào Ngư', 300000),
('V9', N'Salad', 135000),
('V10', N'Cá Hấp', 110000);

INSERT INTO NHANVIEN VALUES 
('NV1', N'Trần Văn Minh', N'Bảo vệ'),
('NV2', N'Nguyễn Thị T', N'Phục vụ'),
('NV3', N'Hà Minh H', N'Phục vụ'),
('NV4', N'Đặng Hồng X', N'Lao công'),
('NV5', N'Trương Văn V', N'Phục vụ'),
('NV6', N'Lê Lợi', N'Thu ngân'),
('NV7', N'Đoàn Viết Y', N'Phục vụ'),
('NV8', N'Huỳnh Lâm B', N'Lao công'),
('NV9', N'Thái Trần V', N'Đầu bếp'),
('NV10', N'Mai Thu N', N'Đầu bếp'),
('NV11', N'Đinh Trần G', N'Phục vụ');	
