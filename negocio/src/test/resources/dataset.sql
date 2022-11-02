insert into administrador_super values ("1", "pepito@gmail.com", "dggs35353", "pepito", "bcbdbdb");
insert into administrador_super values ("2", "pedro@gmail.com", "dggs35353", "pedro", "bcbdbdb");
insert into administrador_super values ("3", "ana@gmail.com", "dggs35353", "ana", "bcbdbdb");
insert into administrador_super values ("4", "laura@gmail.com", "dggs35353", "laura", "bcbdbdb");
insert into administrador_super values ("5", "man@gmail.com", "dggs35353", "manuel", "bcbdbdb");

insert into administrador_teatro values ("1", "juan1@gmail.com", "url1", "Juan1", "341");
insert into administrador_teatro values ("2", "juan2@gmail.com", "url2", "Juan2", "342");
insert into administrador_teatro values ("3", "juan3@gmail.com", "url3", "Juan3", "343");
insert into administrador_teatro values ("4", "juan4@gmail.com", "url4", "Juan4", "344");
insert into administrador_teatro values ("5", "juan5@gmail.com", "url5", "Juan5", "345");

insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Pereira");
insert into ciudad values (3, "Medellin");
insert into ciudad values (4, "Bogota");
insert into ciudad values (5, "Manizales");

insert into horario values (1, "Lunes","2021/01/06", "2021/01/06", "13:00");
insert into horario values (2, "Martes","2021/01/06", "2021/01/10", "11:00");
insert into horario values (3, "Miercoles","2021/01/06", "2021/01/03", "1:00");
insert into horario values (4, "Jueves","2021/01/06", "2021/01/04", "12:00");
insert into horario values (5, "Viernes","2021/01/06", "2021/01/05", "20:00");

insert into pelicula values (1, "andrea, juan, roberto",  "PREVENTA","COMEDIA", "Batman", "Pelicula de superheroe", "gfbfbfb", "fgbegegege");
insert into pelicula values (2, "andrea, juan, roberto",  "PREVENTA","COMEDIA", "Batmn", "Pelicula de superheroe", "gfbfbfb", "fgbegegege");
insert into pelicula values (3, "andrea, juan, roberto", "PREVENTA","TERROR", "Harry potter y la piedra filosofal", "Pelicula de suspenso", "gfbfbfb", "fgbegegege");
insert into pelicula values (4, "andrea, juan, roberto",  "PREVENTA","COMEDIA", "Btman", "Harry potter y el caliz de fuego", "gfbfbfb", "fgbegegege");
insert into pelicula values (5, "andrea, juan, roberto",  "PREVENTA","COMEDIA", "atman", "Pelicula de superheroes", "gfbfbfb", "fgbegegege");


insert into teatro values (1, "Calle sexta #12", "3125679834", "1", 1);
insert into teatro values (2, "Calle novena #18", "3125679834", "1", 2);
insert into teatro values (3, "Calle diagonal #34", "3125679834", "2", 3);
insert into teatro values (4, "Calle norte #3", "3125679834", "2", 4);
insert into teatro values (5, "Calle #9", "3125679834", "3", 5);


insert into distribucion_silla values (1, 10, "eegege", 10, 90);
insert into distribucion_silla values (2, 10, "eegege", 10, 90);
insert into distribucion_silla values (3, 10, "eegege", 10, 90);
insert into distribucion_silla values (4, 10, "eegege", 10, 90);
insert into distribucion_silla values (5, 10, "eegege", 10, 90);

insert into sala values (1,"HABILITADA", "sala1", 1, 1);
insert into sala values (2,"HABILITADA", "sala2", 1, 2);
insert into sala values (3,"HABILITADA", "sala3", 1, 3);
insert into sala values (4,"HABILITADA", "sala4", 1, 4);
insert into sala values (5,"HABILITADA", "sala5", 1, 5);


insert into funcion values (1, 6.500, 1, 1, 1);
insert into funcion values (2, 6500, 2, 2, 2);
insert into funcion values (3, 7000, 3, 3, 3);
insert into funcion values (4, 5000, 4, 4, 4);
insert into funcion values (5, 6.500,5, 5, 5);


insert into cliente values ("123", "lala@gmail.com", "dgdddg", "Laura", "gegeg", "ACTIVO");
insert into cliente values ("344", "pedro@gmail.com", "dgdddg", "Pedro", "gegeg", "ACTIVO");
insert into cliente values ("984", "juan@gmail.com", "dgdddg", "Juan", "gegeg", "ACTIVO");
insert into cliente values ("943", "man@gmail.com", "dgdddg", "Manuel", "gegeg", "ACTIVO");
insert into cliente values ("922", "Luis@gmail.com", "dgdddg", "Luis", "gegeg", "ACTIVO");



insert into cliente_telefonos values ("123", "3123456789", "casa");
insert into cliente_telefonos values ("123", "7456789", "trabajo");
insert into cliente_telefonos values ("344", "3123456700", "casa");
insert into cliente_telefonos values ("922", "3123456776", "personal");
insert into cliente_telefonos values ("922", "3123456757", "local");



insert into cupon values (1, "cupon de bienvenida", "cupon a nuevos clientes del mes", 25, "2022/12/30");
insert into cupon values (2, "cupon del mes", "san valentin", 25, "2022/12/30");
insert into cupon values (3, "cupon del mes", "navidad", 25, "2022/12/30");
insert into cupon values (4, "cupon del mes", "hallowen", 25, "2022/12/30");
insert into cupon values (5, "cupon del mes", "pascua", 25, "2022/12/30");



insert into cupon_cliente values (1, "SIN_USAR", "123", 1);
insert into cupon_cliente values (2, "SIN_USAR", "123", 2);
insert into cupon_cliente values (3, "SIN_USAR", "123", 3);
insert into cupon_cliente values (4, "SIN_USAR", "123", 4);
insert into cupon_cliente values (5, "SIN_USAR", "123", 5);



insert into compra values (1, "2022/06/09", "TARJETA_CREDITO", 20000, "123", 1, 1 );
insert into compra values (2, "2022/06/09", "TARJETA_CREDITO", 20000, "344", 2, 1 );
insert into compra values (3, "2022/06/09", "TARJETA_CREDITO", 20000, "123", 3, 1 );
insert into compra values (4, "2022/06/09", "TARJETA_CREDITO", 20000, "123", 4, 1 );
insert into compra values (5, "2022/06/09", "TARJETA_CREDITO", 20000, "123", 5, 1 );


insert into entrada values (1, 1, "ASISTIO", 1, 6000, 1);
insert into entrada values (2, 1, "ASISTIO", 2, 6000, 1);
insert into entrada values (3, 1, "ASISTIO", 3, 6000, 2);
insert into entrada values (4, 1, "ASISTIO", 4, 6000, 2);
insert into entrada values (5, 1, "ASISTIO", 5, 6000, 2);


insert into confiteria values (1, "Combo 1", 15000, "fbrhrhr");
insert into confiteria values (2, "Combo 2", 10000, "fbrhrhr");
insert into confiteria values (3, "Combo 3", 20000, "fbrhrhr");
insert into confiteria values (4, "Combo 4", 35000, "fbrhrhr");
insert into confiteria values (5, "Combo 5", 6000, "fbrhrhr");


insert into compra_confiteria values (1, 30000, 2, 1, 1);
insert into compra_confiteria values (2, 30000, 2, 1, 1);
insert into compra_confiteria values (3, 30000, 2, 1, 1);
insert into compra_confiteria values (4, 30000, 2, 1, 1);
insert into compra_confiteria values (5, 30000, 2, 1, 1);
