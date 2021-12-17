select * from tabs;
select * from user_sequences;

select * from member;
select * from product;
select * from orders;
select * from ORDER_VIEW;

update order_detail set result = '2' where oseq=5;

select *  from qna;

create table qna(
	qseq number(5) primary key, -- 글번호
	subject varchar2(300), -- 제목
	content varchar2(1000), -- 문의 내용
	reply varchar2(1000), -- 답변 내용
	id varchar2(20), -- 작성자(FK : member.id)
	rep char(1) default '1', -- 1: 답변무 2: 답변유
	indate date default sysdate -- 작성일
);

insert into qna(qseq, subject, content, id)
values(qna_seq.nextVal, '테스트', '질문내용1', 'one');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextVal, '테스트2', '질문내용2', 'two');

insert into qna(qseq, subject, content, id)
values(qna_seq.nextVal, '환불관련', '환불절차 아내부탁드려요... 배송사 선택은 어떻게 되는지도...', 'one');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextVal, '배송관련 문의입니다.', '현재 배송상태와 예상 배송일을 답변 부탁합니다.', 'two');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextVal, '사이즈 교환하고 싶어요.', '사이즈가 예상보다 작습니다. 교환절차를 안내부탁드려요.', 'one');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextVal, '배송이 많이 지연되고 있습니다.', '언제 받을 수 있나요?', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextVal, '불량품 교환 문의', '교환 또는 환불 등의 안내가 필요합니다. 유션안내 부탁드려요.', 'scott');