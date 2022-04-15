-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 14, 2022 alle 20:24
-- Versione del server: 10.4.22-MariaDB
-- Versione PHP: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carsharing`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `car`
--

CREATE TABLE `car` (
  `targa` varchar(7) NOT NULL,
  `id_parcheggio` varchar(10) DEFAULT NULL,
  `cfutente_associato` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `car`
--

INSERT INTO `car` (`targa`, `id_parcheggio`, `cfutente_associato`) VALUES
('aa111zz', '004', NULL),
('aa222bb', NULL, 'fdrtdn98p01f839b'),
('ad843ee', NULL, 'mtrmrc93p01f839b'),
('av225zy', NULL, 'mrtznc01p01f839b'),
('az190ia', '003', NULL),
('az190ka', '003', NULL),
('bf324rt', '010', NULL),
('bf8234h', '009', NULL),
('bh888km', '009', NULL),
('bn342ol', '004', NULL),
('cv786bb', '001', NULL),
('dd234re', '007', NULL),
('dd234se', '003', NULL),
('dl112bn', '006', NULL),
('ff333rr', '006', NULL),
('fr908lk', '005', NULL),
('gl324ra', '010', NULL),
('hf883gg', '005', NULL),
('jj912hd', '007', NULL),
('jl923hu', '002', NULL),
('kd843ff', '005', NULL),
('kk009ll', '004', NULL),
('lk332oi', '007', NULL),
('lk893df', '008', NULL),
('lr436tt', '008', NULL),
('mc109ss', '010', NULL),
('mx495ww', '005', NULL),
('nb012jj', '007', NULL),
('nm090ml', '001', NULL),
('rt194gg', '006', NULL),
('sf934cc', '002', NULL),
('vb348hh', '009', NULL),
('vz449hg', '004', NULL),
('zz111xx', '002', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `lista_prenotazioni`
--

CREATE TABLE `lista_prenotazioni` (
  `ticket` varchar(10) NOT NULL,
  `cf_utente` varchar(16) NOT NULL,
  `targa_auto` varchar(7) NOT NULL,
  `data_inizio_prenotazione` date NOT NULL,
  `data_riconsegna_auto` date NOT NULL,
  `id_parcheggio_prestabilito` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `lista_prenotazioni`
--

INSERT INTO `lista_prenotazioni` (`ticket`, `cf_utente`, `targa_auto`, `data_inizio_prenotazione`, `data_riconsegna_auto`, `id_parcheggio_prestabilito`) VALUES
('5712399', 'mrtznc01p01f839b', 'av225zy', '2022-05-10', '2022-09-19', '008');

-- --------------------------------------------------------

--
-- Struttura della tabella `parcheggio`
--

CREATE TABLE `parcheggio` (
  `id_parcheggio` varchar(10) NOT NULL,
  `localita` varchar(20) NOT NULL,
  `nome_parcheggio` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `parcheggio`
--

INSERT INTO `parcheggio` (`id_parcheggio`, `localita`, `nome_parcheggio`) VALUES
('001', 'Napoli', 'Mergellina Park'),
('002', 'Roma', 'Tufello Show'),
('003', 'Milano', 'Duomo Parking'),
('004', 'Bari', 'Da Nicola'),
('005', 'Messina', 'Parcheggio Nostro'),
('006', 'Imola', 'Parking of the sun'),
('007', 'Asti', 'Giacomo Legame Park'),
('008', 'Firenze', 'Parcheggio Dante'),
('009', 'Vomero', 'Merola Park'),
('010', 'Gorizia', 'Nowhere Park');

-- --------------------------------------------------------

--
-- Struttura della tabella `persona`
--

CREATE TABLE `persona` (
  `codice_fiscale` varchar(16) NOT NULL,
  `data_nascita` date DEFAULT NULL,
  `targa_car_associata` varchar(7) DEFAULT NULL,
  `budget` int(10) DEFAULT NULL,
  `data_iscrizione` date DEFAULT NULL,
  `data_associazione_car` date DEFAULT NULL,
  `nome` varchar(10) NOT NULL,
  `cognome` varchar(10) NOT NULL,
  `tipologia_persona` varchar(10) NOT NULL,
  `cattivo_pagatore` varchar(10) DEFAULT NULL,
  `data_fissata_restituzione` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `persona`
--

INSERT INTO `persona` (`codice_fiscale`, `data_nascita`, `targa_car_associata`, `budget`, `data_iscrizione`, `data_associazione_car`, `nome`, `cognome`, `tipologia_persona`, `cattivo_pagatore`, `data_fissata_restituzione`) VALUES
('bndfba98p01f839b', '1998-09-01', NULL, 93500, '2022-04-02', NULL, 'fabio', 'bandiera', '1', NULL, NULL),
('fdrtdn98p01f839b', '1998-08-01', 'aa222bb', 500, '2022-04-02', '2022-04-01', 'federico', 'tudini', '2', NULL, '2022-04-10'),
('fnrfrc98p01f839b', '2022-07-04', NULL, 755, '2022-04-10', NULL, 'francesco', 'fanara', '2', NULL, NULL),
('mrtznc01p01f839b', '2001-04-11', 'av225zy', 220, '2020-03-30', '2022-03-30', 'martina', 'zunico', '2', NULL, '2022-04-06'),
('mtrmrc93p01f839b', '1993-07-04', 'ad843ee', 500, '2020-03-03', '2022-02-09', 'marco', 'matrullo', '2', NULL, '2022-03-03'),
('vtrplt98p01f839b', '1999-08-01', NULL, 911, '2022-04-12', NULL, 'vittorio', 'pallotta', '2', 'null', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `tipologiapersona`
--

CREATE TABLE `tipologiapersona` (
  `id` varchar(2) NOT NULL,
  `descrizione` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `tipologiapersona`
--

INSERT INTO `tipologiapersona` (`id`, `descrizione`) VALUES
('1', 'erogatore'),
('2', 'utente');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`targa`);

--
-- Indici per le tabelle `lista_prenotazioni`
--
ALTER TABLE `lista_prenotazioni`
  ADD PRIMARY KEY (`ticket`),
  ADD KEY `idParcheggio fk` (`id_parcheggio_prestabilito`),
  ADD KEY `cf utente fk` (`cf_utente`),
  ADD KEY `auto fk` (`targa_auto`);

--
-- Indici per le tabelle `parcheggio`
--
ALTER TABLE `parcheggio`
  ADD PRIMARY KEY (`id_parcheggio`),
  ADD UNIQUE KEY `nome_parcheggio` (`nome_parcheggio`);

--
-- Indici per le tabelle `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`codice_fiscale`),
  ADD KEY `tipopersfk` (`tipologia_persona`);

--
-- Indici per le tabelle `tipologiapersona`
--
ALTER TABLE `tipologiapersona`
  ADD PRIMARY KEY (`id`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `lista_prenotazioni`
--
ALTER TABLE `lista_prenotazioni`
  ADD CONSTRAINT `auto fk` FOREIGN KEY (`targa_auto`) REFERENCES `car` (`targa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `cf utente fk` FOREIGN KEY (`cf_utente`) REFERENCES `persona` (`codice_fiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idParcheggio fk` FOREIGN KEY (`id_parcheggio_prestabilito`) REFERENCES `parcheggio` (`id_parcheggio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `tipopersfk` FOREIGN KEY (`tipologia_persona`) REFERENCES `tipologiapersona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
