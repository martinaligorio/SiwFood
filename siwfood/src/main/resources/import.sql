INSERT INTO cuoco (id, nome, cognome, datanascita, urlimage) VALUES (nextval('cuoco_seq'),'Carlo', 'Cracco', '08/10/1965', '/images/carlo_cracco.jpg'); 

INSERT INTO cuoco (id, nome, cognome, datanascita, urlimage) VALUES (nextval('cuoco_seq'),'Antonino', 'Cannavacciuolo', '16/04/1975', '/images/cannavacciuolo.jpg');




INSERT INTO ricetta (id, nome, urlimage, descrizione,  cuoco_id) VALUES (nextval('ricetta_seq'), 'Carbonara', '/images/Amatriciana.jpg', 'La carbonara e un condimento per la pasta tipico della tradizione gastronomica di Roma a base di uova guanciale e pecorino', (SELECT id FROM cuoco WHERE nome = 'Antonino' AND cognome = 'Cannavacciuolo'));

INSERT INTO ricetta (id, nome, urlimage, descrizione,  cuoco_id) VALUES (nextval('ricetta_seq'), 'Amatriciana','/images/Carbonara.jpg','L amatriciana e un condimento per la pasta tipico della tradizione gastronomica di Amatrice a base di pomodoro guanciale e pecorino', (SELECT id FROM cuoco WHERE nome = 'Carlo' AND cognome = 'Cracco'));




