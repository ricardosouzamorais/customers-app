-- Refactoring database to create fields for helping on filter data easily
ALTER TABLE customer ADD country VARCHAR(50);

UPDATE 	customer
SET 	country = 'Morocco'
WHERE	phone LIKE '(212)%';

UPDATE 	customer
SET 	country = 'Cameroon'
where	phone LIKE '(237)%';

UPDATE 	customer
SET 	country = 'Ethiopia'
WHERE	phone LIKE '(251)%';

UPDATE 	customer
SET 	country = 'Uganda'
WHERE	phone LIKE '(256)%';

UPDATE 	customer
SET 	country = 'Mozambique'
WHERE	phone LIKE '(258)%';