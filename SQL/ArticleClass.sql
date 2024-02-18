use MyBlog;

create Table ArticleClass (
	articleclass_id int not null primary key identity(1,1),
	articleclass_name nvarchar(50) not null UNIQUE,
	);