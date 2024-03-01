use MyBlog;

create Table Account (
	accountId int not null primary key identity(1,1),
	accountNumber varchar(30) not null,
	accountPassword varchar(MAX) not null,
	role varchar(30) not null,
	);