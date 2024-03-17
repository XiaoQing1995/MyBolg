use MyBlog;

create Table ArticleClass (
	articleClassId int not null primary key identity(1,1),
	articleClassName nvarchar(50) not null UNIQUE,
	);