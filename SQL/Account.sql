use MyBlog;

create Table Account (
	account_id int not null primary key identity(1,1),
	account_number varchar(30) not null,
	account_password varchar(30) not null,
	);