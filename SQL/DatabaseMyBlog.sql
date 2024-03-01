use MyBlog;

create Table Account (
	accountId int not null primary key identity(1,1),
	accountNumber varchar(30) not null,
	accountPassword varchar(MAX) not null,
	);

create Table ArticleClass (
	articleClassId int not null primary key identity(1,1),
	articleClassName nvarchar(50) not null UNIQUE,
	);


create Table Article (
	articleId int not null primary key identity(1,1),
	articleTitle nvarchar(200) not null,
	articleContent nvarchar(max) not null,
	articleDate date not null,
	articleImg varbinary(max) not null,
	articleClassId int not null references ArticleClass(articleClassId),
	);