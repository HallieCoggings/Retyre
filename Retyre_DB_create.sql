CREATE TABLE `Vehicule` (
  `liscencePlate` varchar[40] PRIMARY KEY,
  `id_typeVehicule` integer,
  `id_owner` integer,
  `distance` integer,
  `dateCiculation` date,
  `pieces` integer
);

CREATE TABLE `TypeVehicule` (
  `id_Vehicule` integer PRIMARY KEY,
  `Brand` varchar[40] NOT NULL,
  `model` varchar[40] NOT NULL,
  `Energy` varchar[40],
  `Gear` varchar[40],
  `nbDoor` integer,
  `nbPlaces` integer,
  `Power` integer
);

CREATE TABLE `Owner` (
  `id_Owner` integer PRIMARY KEY,
  `name` VARCHAR[255] NOT NULL,
  `first_name` VARCHAR[255] NOT NULL,
  `DOB` date,
  `Phone` varchar[12]
);

CREATE TABLE `Piece` (
  `id_Piece` integer PRIMARY KEY,
  `Reference` varchar[40] NOT NULL,
  `supplier_id` varcchar[40],
  `Descripition` varchar[255],
  `category` varchar[40],
  `price_HT` float,
  `price_AT` float
);

CREATE TABLE `Intervention` (
  `id_Intervention` integer PRIMARY KEY,
  `id_Vehicule` integer,
  `dateIntervention` date,
  `type` varchar[255],
  `finished` boolean,
  `worker` integer
);

CREATE TABLE `Supplier` (
  `SIRET` varchar[14] PRIMARY KEY,
  `Name` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL
);

CREATE TABLE `Employee` (
  `id_Employee` interger PRIMARY KEY,
  `Name` varchar(255),
  `First_Name` varchar(255),
  `speciliaty` varchar(255)
);

ALTER TABLE `Vehicule` ADD FOREIGN KEY (`id_owner`) REFERENCES `Owner` (`id_Owner`);

ALTER TABLE `Vehicule` ADD FOREIGN KEY (`id_typeVehicule`) REFERENCES `TypeVehicule` (`id_Vehicule`);

CREATE TABLE `Vehicule_Piece` (
  `Vehicule_pieces` integer,
  `Piece_id_Piece` integer,
  PRIMARY KEY (`Vehicule_pieces`, `Piece_id_Piece`)
);

ALTER TABLE `Vehicule_Piece` ADD FOREIGN KEY (`Vehicule_pieces`) REFERENCES `Vehicule` (`pieces`);

ALTER TABLE `Vehicule_Piece` ADD FOREIGN KEY (`Piece_id_Piece`) REFERENCES `Piece` (`id_Piece`);


ALTER TABLE `Intervention` ADD FOREIGN KEY (`id_Intervention`) REFERENCES `Vehicule` (`liscencePlate`);

ALTER TABLE `Piece` ADD FOREIGN KEY (`supplier_id`) REFERENCES `Supplier` (`SIRET`);

ALTER TABLE `Intervention` ADD FOREIGN KEY (`worker`) REFERENCES `Employee` (`id_Employee`);
