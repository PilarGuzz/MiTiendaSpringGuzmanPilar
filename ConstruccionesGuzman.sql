-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: mysqlDawes:3306
-- Tiempo de generación: 08-11-2022 a las 08:25:33
-- Versión del servidor: 5.7.22
-- Versión de PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ConstruccionesGuzman`
--
CREATE DATABASE miTiendaSpringGuzmanPilar;
USE `miTiendaSpringGuzmanPilar`;

CREATE USER 'GuzmanPilar'@'%' IDENTIFIED BY 'GuzmanPilar';
GRANT ALL PRIVILEGES ON miTiendaSpringGuzmanPilar.* to 'GuzmanPilar'@'%';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `name`, `description`) VALUES
(1, 'Temp Fencing, Decorative Fencing and Gates', '11-300 - Fluid Waste Treatment'),
(2, 'Retaining Wall and Brick Pavers', '1-540 - Construction Aids'),
(3, 'EIFS', '16-050 - Basic Electrical Materials and Methods'),
(4, 'Elevator', '11-470 - Darkroom Equipment'),
(5, 'Granite Surfaces', '1-540 - Construction Aids'),
(6, 'Sitework & Site Utilities', '5-400 - Cold-Formed Metal Framing'),
(7, 'Termite Control', '11-460 - Unit Kitchens'),
(8, 'Kitchen', '2-312 - Rough Grading'),
(9, 'Epoxy Flooring', '1-517 - Temporary Telephone'),
(10, 'Bathroom', '4-200 - Masonry Units');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `material`
--

CREATE TABLE `material` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `stock` int(50) DEFAULT 50,
  `img` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `material`
--

INSERT INTO `material` (`id`, `name`, `description`, `price`, `category`) VALUES
(1, 'Backhoe', '13-175 - Ice Rinks', '68.87', 5),
(2, 'Excavator', '2-813 - Lawn Sprinkling and Irrigation', '11.71', 4),
(3, 'Dragline', '4-200 - Masonry Units', '203.96', 9),
(4, 'Trencher', '1-520 - Construction Facilities', '261.96', 9),
(5, 'Dragline2', '5 - Metals', '108.08', 7),
(6, 'Trencher2', '7 - Thermal and Moisture Protection', '58.61', 1),
(7, 'Dragline3', '13-600 - Solar and Wind Energy Equipment', '41.16', 5),
(8, 'Dragline4', '1-640 - Owner Furnished Products', '212.73', 5),
(9, 'Crawler', '12-600 - Multiple Seating', '243.68', 1),
(10, 'Crawler2', '6-050 - Basic Wood and Plastic Materials', '161.83', 5),
(11, 'Bulldozer', '10-550 - Postal Specialties', '262.65', 7),
(12, 'Scraper', '13-175 - Ice Rinks', '188.48', 10),
(13, 'Excavator2', '10-240 - Grilles and Screens', '150.09', 6),
(14, 'Dump Truck', '6-050 - Basic Wood and Plastic Materials', '288.22', 4),
(15, 'Crawler22', '8-700 - Hardware', '223.35', 2),
(16, 'Bulldozer2', '4-600 - Corrosion-Resistant Masonry', '147.53', 6),
(17, 'Bulldozer3', '2-923 - Seeding and Soil Supplements', '142.64', 10),
(18, 'Excavator3', '10-290 - Pest Control', '93.09', 4),
(19, 'Crawler10', '2-240 - Dewatering', '141.96', 4),
(20, 'Dragline5', '1-000 - Purpose', '83.12', 8),
(21, 'Skid-Steer', '13-030 - Special Purpose Rooms', '74.10', 3),
(22, 'Compactor2', '13-150 - Swimming Pools', '63.90', 1),
(23, 'Skid-Steer20', '4-400 - Stone', '118.89', 9),
(24, 'Excavator10', '11-870 - Agricultural Equipment', '204.04', 4),
(25, 'Crawler15', '14-400 - Lifts', '157.13', 5),
(26, 'Backina', '2-935 - Plant Maintenance', '64.21', 4),
(27, 'Blockfloor', '2-822 - Ornamental Metal Fences and Gates', '161.91', 8),
(28, 'Scraper10', '3-310 - Expansion Joints', '34.41', 5),
(29, 'Dragline10', '5-200 - Metal Joists', '62.70', 4),
(30, 'Trencher10', '8-600 - Skylights', '266.62', 5),
(31, 'Compactor3', '10-670 - Storage Shelving', '243.65', 1),
(32, 'Blancolor', '1-013 - Project Coordinator', '59.38', 7),
(33, 'Backhoe2', '11-870 - Agricultural Equipment', '21.33', 2),
(34, 'Grader', '2-000 - General', '224.66', 1),
(35, 'Backhoe3', '3-210 - Cast-In-Place Concrete', '268.96', 10),
(36, 'Crawler35', '13-185 - Kennels and Animal Shelters', '295.62', 10),
(37, 'Grader10', '17-030 - Bond', '145.99', 7),
(38, 'Scraper15', '4 - Masonry', '18.20', 10),
(39, 'Scraper20', '14-400 - Lifts', '298.38', 1),
(40, 'Scraper25', '10-750 - Telephone Specialties', '229.78', 3),
(41, 'Grader20', '2-915 - Mulch', '229.37', 3),
(42, 'Scraper30', '1-011 - Project Engineer', '219.04', 4),
(43, 'Bulldozer5', '9-050 - Basic Finish Materials and Methods', '156.44', 8),
(44, 'Excavator15', '16-300 - Transmission and Distribution', '93.80', 10),
(45, 'Trencher15', '11-150 - Parking Control Equipment', '172.01', 5),
(46, 'Bulldozer10', '1-010 - Project Manager', '4.26', 4),
(47, 'Bulldozer15', '13-030 - Special Purpose Rooms', '207.62', 3),
(48, 'Trencher20', '1-540 - Construction Aids', '113.49', 3),
(49, 'Trencher25', '2-312 - Rough Grading', '85.49', 6),
(50, 'Bulldozer20', '1-712 - Local Conditions', '46.17', 2),
(51, 'Compactor10', '1-904 - Hazardous Materials Removal', '196.00', 7),
(52, 'Excavator20', '13-200 - Storage Tanks', '282.91', 5),
(53, 'Scraper40', '12-050 - Fabrics', '164.11', 1),
(54, 'Dragline15', '7-400 - Roofing and Siding Panels', '102.19', 4),
(55, 'Dragline20', '13 - Special Construction', '262.24', 3),
(56, 'Compactor20', '2-820 - Fences and Gates', '272.92', 4),
(57, 'Scraper45', '15-300 - Fire Protection Piping', '26.60', 2),
(58, 'Dragline30', '2-935 - Plant Maintenance', '100.98', 4),
(59, 'Skid-Steer40', '10-600 - Partitions', '27.23', 3),
(60, 'Bulldozer25', '11-170 - Solid Waste Handling Equipment', '245.26', 2),
(61, 'Crawler50', '11-040 - Ecclesiastical Equipment', '239.04', 2),
(62, 'Bulldozer30', '13-400 - Measurement and Control', '123.46', 4),
(63, 'Dragline35', '2-310 - Grading', '146.00', 2),
(64, 'Scraper50', '6-200 - Finish Carpentry', '76.01', 2),
(65, 'Bulldozer40', '1-600 - Product Requirements', '190.16', 1),
(66, 'Dragline32', '3-330 - Poured Concrete Basement Walls', '126.26', 7),
(67, 'Dump Truck 200', '2-750 - Concrete Pads and Walks', '83.23', 10),
(68, 'Scraper55', '13-260 - Sludge Conditioning Systems', '103.40', 8),
(69, 'Dragline50', '2-300 - Earthwork', '40.39', 6),
(70, 'Scraper60', '4-500 - Refractories', '10.32', 2),
(71, 'Backhoe43', '5-700 - Ornamental Metal', '215.94', 1),
(72, 'Grader50', '2-200 - Site Preparation', '236.89', 3),
(73, 'Bulldozer45', '3-230 - Anchor Bolts', '156.63', 7),
(74, 'Backhoe150', '3-500 - Cementitious Decks', '26.01', 6),
(75, 'Bulldozer50', '13-240 - Oxygenation Systems', '138.15', 5),
(76, 'Compactor50', '10-290 - Pest Control', '57.14', 9),
(77, 'Excavator19', '4-600 - Corrosion-Resistant Masonry', '293.83', 2),
(78, 'Grader70', '15-900 - HVAC Instruments and Controls', '163.33', 3),
(79, 'Trencher50', '2-824 - Wire Fences and Gates', '275.12', 5),
(80, 'Scraper75', '1-550 - Vehicular Access and Parking', '157.02', 5),
(81, 'Crawler100', '10-800 - Toilet, Bath, and Laundry ', '16.48', 2),
(82, 'Grader80', '11-190 - Detention Equipment', '212.72', 5),
(83, 'Excavator25', '11-010 - Maintenance Equipment', '145.00', 5),
(84, 'Grader100', '11-040 - Ecclesiastical Equipment', '234.42', 9),
(85, 'Dragline100', '15-900 - HVAC Instruments and Controls', '244.90', 8),
(86, 'Backhoe20', '11-130 - Audio-Visual Equipment', '217.63', 8),
(87, 'Backhoe15', '8-050 - Basic Door and Window Materials', '155.53', 5),
(88, 'Bulldozer100', '16 - Electrical', '285.00', 3),
(89, 'Excavator30', '16-200 - Electrical Power', '245.02', 6),
(90, 'Skid-Steer50', '2-812 - Drip Irrigation', '2.09', 8),
(91, 'Grader150', '2-740 - Flexible Pavement Asphalt Pavement', '30.35', 1),
(92, 'Trencher60', '11-030 - Teller and Service Equipment', '79.13', 3),
(93, 'Skid-Steer60', '7 - Thermal and Moisture Protection', '43.92', 3),
(94, 'Excavator3000', '3-350 - Concrete Finishing', '160.75', 8),
(95, 'Skid-Steer70', '2-310 - Grading', '259.05', 2),
(96, 'Scraper80', '5-100 - Structural Metals', '132.56', 10),
(97, 'Dump Truck 300', '2-924 - Sodding', '30.61', 2),
(98, 'Dragline150', '10-800 - Toilet, Bath, and Laundry ', '171.38', 8),
(99, 'Dragline200', '2-625 - Retaining Wall Drainage Piping', '100.41', 4),
(100, 'Trencher75', '1-560 - Temporary Barriers and Enclosures', '200.51', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`username`, `pass`, `name`, `email`, `admin`, `role`, `verification_code`, `enabled`) VALUES
('inma', 'd40dbcae0e7088fc4a7e1768cf8771da', 'inma', 'inma@gmail.com', 1, 'ADMIN', null, 1),
('paco', '311020666a5776c57d265ace682dc46d', 'paco', 'paco@gmail.com', 0, 'USER', null, 1),
('pilar', '31c7d084f0460fcde98ee9314fc8ef30', 'pilar', 'pilar@gmail.com', 1, 'ADMIN', null, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orders`
--

CREATE TABLE `order` (
  `id` int(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  `iva` real NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `purchase`
--

CREATE TABLE `purchase` (
  `idOrder` int(50) NOT NULL,
  `materialCode` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `fk_cat` (`category`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);
  

--
-- Indices de la tabla `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`idOrder`,`materialCode`),
  ADD KEY `fk_material` (`materialCode`),
  ADD KEY `fk_Order` (`idOrder`);

--

-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `material`
--
ALTER TABLE `material`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT de la tabla `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `material`
--
ALTER TABLE `material`
  ADD CONSTRAINT `fk_cat` FOREIGN KEY (`category`) REFERENCES `category` (`id`) ON DELETE CASCADE;


--
-- Filtros para la tabla `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_fk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
--
-- Filtros para la tabla `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`materialCode`) REFERENCES `material` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`idOrder`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
