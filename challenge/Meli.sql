INSERT INTO public.category( max, name, rate)
	VALUES (500000, 'NEW', 0.15);
INSERT INTO public.category( max, name, rate)
	VALUES (1000000, 'FREQUENT', 0.10);
INSERT INTO public.category( max, name, rate)
	VALUES (5000000, 'PREMIUM', 0.05);	



INSERT INTO public.users(nombre, total_prestamos, volumen_prestamos) 
VALUES ('maria', 1, 50000);
INSERT INTO public.users(nombre, total_prestamos, volumen_prestamos)
VALUES ('carla', 1, 30000);
INSERT INTO public.users(nombre, total_prestamos, volumen_prestamos)
VALUES ('diago', 1, 40000);
INSERT INTO public.users(nombre, total_prestamos, volumen_prestamos)
VALUES ('maria', 2, 250000);
INSERT INTO public.users(nombre, total_prestamos, volumen_prestamos)
VALUES ('pablo', 3, 350000);
INSERT INTO public.users(nombre, total_prestamos, volumen_prestamos)
VALUES ('raul', 4, 500000);
INSERT INTO public.users(nombre, total_prestamos, volumen_prestamos)
VALUES ('carlos', 6, 520000);
INSERT INTO public.users(nombre, total_prestamos, volumen_prestamos)
VALUES ('juan', 7, 750000);	
INSERT INTO public.users(nombre, total_prestamos, volumen_prestamos)
VALUES ('ana', 9, 950000);

SELECT loan.id, loan.amount,loan.term, category.rate, loan.user_id,category.name, loan.date
from loan
INNER JOIN ON categoy.id = loan.category_id
WHERE loan.date BETWEEN :from AND :to
