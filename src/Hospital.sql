
create table specialties(
	id_especiality INT PRIMARY KEY auto_increment,
    name_especiality varchar(40) not null,
    description_especiality varchar (40) not null
);

create table medics (
	id_medic INT PRIMARY KEY auto_increment,
    medic_name varchar(40) not null, 
    last_name_medic varchar(40) not null, 
    id_spec int,
    constraint fk_id_especiality foreign key (id_spec) references specialties(id_especiality)
);

create table patients (
	id_patient int primary key auto_increment, 
	patient_name varchar(40) not null, 
	last_name_patient varchar (40)not null, 
    birth_date datetime not null,
    document_identification varchar (15)not null
    
);

create table appointments (
	id_appointment int primary key auto_increment,
    appointment_date date not null,
    appointment_time time not null,
    description varchar(100) not null,
    id_medic_appointment int,
    id_patient_appointment int,
    constraint fk_id_patient foreign key(id_patient_appointment) references patients(id_patient),
    constraint fk_id_medic foreign key(id_medic_appointment) references medics(id_medic)
);
