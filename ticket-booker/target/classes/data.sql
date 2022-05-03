INSERT INTO movie(id, duration, title) VALUES
    (1, 153, 'Pirates of the Caribbean'),
    (2, 136, 'Matrix'),
    (3, 178, 'Lord of the Rings');

INSERT INTO room(id) VALUES
    (1),
    (2),
    (3);

INSERT INTO screening(id, time, movie_id, room_id) VALUES
    (1, '2022-08-29T19:00:00', 1, 1),
    (2, '2022-08-29T18:00:00', 2, 2),
    (3, '2022-08-29T17:00:00', 3, 3),
    (4, '2022-08-29T22:00:00', 2, 1),
    (5, '2022-08-29T20:30:00', 3, 2),
    (6, '2022-08-29T21:00:00', 1, 3);

INSERT INTO seat(id, room_id, row, number) VALUES
    (1, 1, 1, 1),
    (2, 1, 1, 2),
    (3, 1, 1, 3),
    (4, 1, 1, 4),
    (5, 1, 1, 5),
    (6, 1, 1, 6),
    (7, 1, 1, 7),
    (8, 1, 1, 8),
    (9, 1, 1, 9),
    (10, 1, 1, 10),
    (11, 1, 1, 11),
    (12, 1, 1, 12),
    (13, 1, 1, 13),
    (14, 1, 1, 14),
    (15, 1, 1, 15),
    (16, 1, 1, 16),
    (17, 1, 1, 17),
    (18, 1, 1, 18),
    (19, 1, 1, 19),
    (20, 1, 1, 20),
    (21, 1, 2, 1),
    (22, 1, 2, 2),
    (23, 1, 2, 3),
    (24, 1, 2, 4),
    (25, 1, 2, 5),
    (26, 1, 2, 6),
    (27, 1, 2, 7),
    (28, 1, 2, 8),
    (29, 1, 2, 9),
    (30, 1, 2, 10),
    (31, 1, 2, 11),
    (32, 1, 2, 12),
    (33, 1, 2, 13),
    (34, 1, 2, 14),
    (35, 1, 2, 15),
    (36, 1, 2, 16),
    (37, 1, 2, 17),
    (38, 1, 2, 18),
    (39, 1, 2, 19),
    (40, 1, 2, 20),
    (41, 1, 3, 1),
    (42, 1, 3, 2),
    (43, 1, 3, 3),
    (44, 1, 3, 4),
    (45, 1, 3, 5),
    (46, 1, 3, 6),
    (47, 1, 3, 7),
    (48, 1, 3, 8),
    (49, 1, 3, 9),
    (50, 1, 3, 10),
    (51, 1, 3, 11),
    (52, 1, 3, 12),
    (53, 1, 3, 13),
    (54, 1, 3, 14),
    (55, 1, 3, 15),
    (56, 1, 3, 16),
    (57, 1, 3, 17),
    (58, 1, 3, 18),
    (59, 1, 3, 19),
    (60, 1, 3, 20),
    (61, 1, 4, 1),
    (62, 1, 4, 2),
    (63, 1, 4, 3),
    (64, 1, 4, 4),
    (65, 1, 4, 5),
    (66, 1, 4, 6),
    (67, 1, 4, 7),
    (68, 1, 4, 8),
    (69, 1, 4, 9),
    (70, 1, 4, 10),
    (71, 1, 4, 11),
    (72, 1, 4, 12),
    (73, 1, 4, 13),
    (74, 1, 4, 14),
    (75, 1, 4, 15),
    (76, 1, 4, 16),
    (77, 1, 4, 17),
    (78, 1, 4, 18),
    (79, 1, 4, 19),
    (80, 1, 4, 20),
    (81, 1, 5, 1),
    (82, 1, 5, 2),
    (83, 1, 5, 3),
    (84, 1, 5, 4),
    (85, 1, 5, 5),
    (86, 1, 5, 6),
    (87, 1, 5, 7),
    (88, 1, 5, 8),
    (89, 1, 5, 9),
    (90, 1, 5, 10),
    (91, 1, 5, 11),
    (92, 1, 5, 12),
    (93, 1, 5, 13),
    (94, 1, 5, 14),
    (95, 1, 5, 15),
    (96, 1, 5, 16),
    (97, 1, 5, 17),
    (98, 1, 5, 18),
    (99, 1, 5, 19),
    (100, 1, 5, 20),
    (101, 1, 6, 1),
    (102, 1, 6, 2),
    (103, 1, 6, 3),
    (104, 1, 6, 4),
    (105, 1, 6, 5),
    (106, 1, 6, 6),
    (107, 1, 6, 7),
    (108, 1, 6, 8),
    (109, 1, 6, 9),
    (110, 1, 6, 10),
    (111, 1, 6, 11),
    (112, 1, 6, 12),
    (113, 1, 6, 13),
    (114, 1, 6, 14),
    (115, 1, 6, 15),
    (116, 1, 6, 16),
    (117, 1, 6, 17),
    (118, 1, 6, 18),
    (119, 1, 6, 19),
    (120, 1, 6, 20),
    (121, 1, 7, 1),
    (122, 1, 7, 2),
    (123, 1, 7, 3),
    (124, 1, 7, 4),
    (125, 1, 7, 5),
    (126, 1, 7, 6),
    (127, 1, 7, 7),
    (128, 1, 7, 8),
    (129, 1, 7, 9),
    (130, 1, 7, 10),
    (131, 1, 7, 11),
    (132, 1, 7, 12),
    (133, 1, 7, 13),
    (134, 1, 7, 14),
    (135, 1, 7, 15),
    (136, 1, 7, 16),
    (137, 1, 7, 17),
    (138, 1, 7, 18),
    (139, 1, 7, 19),
    (140, 1, 7, 20),
    (141, 1, 8, 1),
    (142, 1, 8, 2),
    (143, 1, 8, 3),
    (144, 1, 8, 4),
    (145, 1, 8, 5),
    (146, 1, 8, 6),
    (147, 1, 8, 7),
    (148, 1, 8, 8),
    (149, 1, 8, 9),
    (150, 1, 8, 10),
    (151, 1, 8, 11),
    (152, 1, 8, 12),
    (153, 1, 8, 13),
    (154, 1, 8, 14),
    (155, 1, 8, 15),
    (156, 1, 8, 16),
    (157, 1, 8, 17),
    (158, 1, 8, 18),
    (159, 1, 8, 19),
    (160, 1, 8, 20),
    (161, 1, 9, 1),
    (162, 1, 9, 2),
    (163, 1, 9, 3),
    (164, 1, 9, 4),
    (165, 1, 9, 5),
    (166, 1, 9, 6),
    (167, 1, 9, 7),
    (168, 1, 9, 8),
    (169, 1, 9, 9),
    (170, 1, 9, 10),
    (171, 1, 9, 11),
    (172, 1, 9, 12),
    (173, 1, 9, 13),
    (174, 1, 9, 14),
    (175, 1, 9, 15),
    (176, 1, 9, 16),
    (177, 1, 9, 17),
    (178, 1, 9, 18),
    (179, 1, 9, 19),
    (180, 1, 9, 20),
    (181, 1, 10, 1),
    (182, 1, 10, 2),
    (183, 1, 10, 3),
    (184, 1, 10, 4),
    (185, 1, 10, 5),
    (186, 1, 10, 6),
    (187, 1, 10, 7),
    (188, 1, 10, 8),
    (189, 1, 10, 9),
    (190, 1, 10, 10),
    (191, 1, 10, 11),
    (192, 1, 10, 12),
    (193, 1, 10, 13),
    (194, 1, 10, 14),
    (195, 1, 10, 15),
    (196, 1, 10, 16),
    (197, 1, 10, 17),
    (198, 1, 10, 18),
    (199, 1, 10, 19),
    (200, 1, 10, 20),
    (201, 2, 1, 1),
    (202, 2, 1, 2),
    (203, 2, 1, 3),
    (204, 2, 1, 4),
    (205, 2, 1, 5),
    (206, 2, 1, 6),
    (207, 2, 1, 7),
    (208, 2, 1, 8),
    (209, 2, 1, 9),
    (210, 2, 1, 10),
    (211, 2, 1, 11),
    (212, 2, 1, 12),
    (213, 2, 1, 13),
    (214, 2, 1, 14),
    (215, 2, 1, 15),
    (216, 2, 1, 16),
    (217, 2, 1, 17),
    (218, 2, 1, 18),
    (219, 2, 1, 19),
    (220, 2, 1, 20),
    (221, 2, 2, 1),
    (222, 2, 2, 2),
    (223, 2, 2, 3),
    (224, 2, 2, 4),
    (225, 2, 2, 5),
    (226, 2, 2, 6),
    (227, 2, 2, 7),
    (228, 2, 2, 8),
    (229, 2, 2, 9),
    (230, 2, 2, 10),
    (231, 2, 2, 11),
    (232, 2, 2, 12),
    (233, 2, 2, 13),
    (234, 2, 2, 14),
    (235, 2, 2, 15),
    (236, 2, 2, 16),
    (237, 2, 2, 17),
    (238, 2, 2, 18),
    (239, 2, 2, 19),
    (240, 2, 2, 20),
    (241, 2, 3, 1),
    (242, 2, 3, 2),
    (243, 2, 3, 3),
    (244, 2, 3, 4),
    (245, 2, 3, 5),
    (246, 2, 3, 6),
    (247, 2, 3, 7),
    (248, 2, 3, 8),
    (249, 2, 3, 9),
    (250, 2, 3, 10),
    (251, 2, 3, 11),
    (252, 2, 3, 12),
    (253, 2, 3, 13),
    (254, 2, 3, 14),
    (255, 2, 3, 15),
    (256, 2, 3, 16),
    (257, 2, 3, 17),
    (258, 2, 3, 18),
    (259, 2, 3, 19),
    (260, 2, 3, 20),
    (261, 2, 4, 1),
    (262, 2, 4, 2),
    (263, 2, 4, 3),
    (264, 2, 4, 4),
    (265, 2, 4, 5),
    (266, 2, 4, 6),
    (267, 2, 4, 7),
    (268, 2, 4, 8),
    (269, 2, 4, 9),
    (270, 2, 4, 10),
    (271, 2, 4, 11),
    (272, 2, 4, 12),
    (273, 2, 4, 13),
    (274, 2, 4, 14),
    (275, 2, 4, 15),
    (276, 2, 4, 16),
    (277, 2, 4, 17),
    (278, 2, 4, 18),
    (279, 2, 4, 19),
    (280, 2, 4, 20),
    (281, 2, 5, 1),
    (282, 2, 5, 2),
    (283, 2, 5, 3),
    (284, 2, 5, 4),
    (285, 2, 5, 5),
    (286, 2, 5, 6),
    (287, 2, 5, 7),
    (288, 2, 5, 8),
    (289, 2, 5, 9),
    (290, 2, 5, 10),
    (291, 2, 5, 11),
    (292, 2, 5, 12),
    (293, 2, 5, 13),
    (294, 2, 5, 14),
    (295, 2, 5, 15),
    (296, 2, 5, 16),
    (297, 2, 5, 17),
    (298, 2, 5, 18),
    (299, 2, 5, 19),
    (300, 2, 5, 20),
    (301, 2, 6, 1),
    (302, 2, 6, 2),
    (303, 2, 6, 3),
    (304, 2, 6, 4),
    (305, 2, 6, 5),
    (306, 2, 6, 6),
    (307, 2, 6, 7),
    (308, 2, 6, 8),
    (309, 2, 6, 9),
    (310, 2, 6, 10),
    (311, 2, 6, 11),
    (312, 2, 6, 12),
    (313, 2, 6, 13),
    (314, 2, 6, 14),
    (315, 2, 6, 15),
    (316, 2, 6, 16),
    (317, 2, 6, 17),
    (318, 2, 6, 18),
    (319, 2, 6, 19),
    (320, 2, 6, 20),
    (321, 2, 7, 1),
    (322, 2, 7, 2),
    (323, 2, 7, 3),
    (324, 2, 7, 4),
    (325, 2, 7, 5),
    (326, 2, 7, 6),
    (327, 2, 7, 7),
    (328, 2, 7, 8),
    (329, 2, 7, 9),
    (330, 2, 7, 10),
    (331, 2, 7, 11),
    (332, 2, 7, 12),
    (333, 2, 7, 13),
    (334, 2, 7, 14),
    (335, 2, 7, 15),
    (336, 2, 7, 16),
    (337, 2, 7, 17),
    (338, 2, 7, 18),
    (339, 2, 7, 19),
    (340, 2, 7, 20),
    (341, 2, 8, 1),
    (342, 2, 8, 2),
    (343, 2, 8, 3),
    (344, 2, 8, 4),
    (345, 2, 8, 5),
    (346, 2, 8, 6),
    (347, 2, 8, 7),
    (348, 2, 8, 8),
    (349, 2, 8, 9),
    (350, 2, 8, 10),
    (351, 2, 8, 11),
    (352, 2, 8, 12),
    (353, 2, 8, 13),
    (354, 2, 8, 14),
    (355, 2, 8, 15),
    (356, 2, 8, 16),
    (357, 2, 8, 17),
    (358, 2, 8, 18),
    (359, 2, 8, 19),
    (360, 2, 8, 20),
    (361, 2, 9, 1),
    (362, 2, 9, 2),
    (363, 2, 9, 3),
    (364, 2, 9, 4),
    (365, 2, 9, 5),
    (366, 2, 9, 6),
    (367, 2, 9, 7),
    (368, 2, 9, 8),
    (369, 2, 9, 9),
    (370, 2, 9, 10),
    (371, 2, 9, 11),
    (372, 2, 9, 12),
    (373, 2, 9, 13),
    (374, 2, 9, 14),
    (375, 2, 9, 15),
    (376, 2, 9, 16),
    (377, 2, 9, 17),
    (378, 2, 9, 18),
    (379, 2, 9, 19),
    (380, 2, 9, 20),
    (381, 2, 10, 1),
    (382, 2, 10, 2),
    (383, 2, 10, 3),
    (384, 2, 10, 4),
    (385, 2, 10, 5),
    (386, 2, 10, 6),
    (387, 2, 10, 7),
    (388, 2, 10, 8),
    (389, 2, 10, 9),
    (390, 2, 10, 10),
    (391, 2, 10, 11),
    (392, 2, 10, 12),
    (393, 2, 10, 13),
    (394, 2, 10, 14),
    (395, 2, 10, 15),
    (396, 2, 10, 16),
    (397, 2, 10, 17),
    (398, 2, 10, 18),
    (399, 2, 10, 19),
    (400, 2, 10, 20),
    (401, 3, 1, 1),
    (402, 3, 1, 2),
    (403, 3, 1, 3),
    (404, 3, 1, 4),
    (405, 3, 1, 5),
    (406, 3, 1, 6),
    (407, 3, 1, 7),
    (408, 3, 1, 8),
    (409, 3, 1, 9),
    (410, 3, 1, 10),
    (411, 3, 1, 11),
    (412, 3, 1, 12),
    (413, 3, 1, 13),
    (414, 3, 1, 14),
    (415, 3, 1, 15),
    (416, 3, 1, 16),
    (417, 3, 1, 17),
    (418, 3, 1, 18),
    (419, 3, 1, 19),
    (420, 3, 1, 20),
    (421, 3, 2, 1),
    (422, 3, 2, 2),
    (423, 3, 2, 3),
    (424, 3, 2, 4),
    (425, 3, 2, 5),
    (426, 3, 2, 6),
    (427, 3, 2, 7),
    (428, 3, 2, 8),
    (429, 3, 2, 9),
    (430, 3, 2, 10),
    (431, 3, 2, 11),
    (432, 3, 2, 12),
    (433, 3, 2, 13),
    (434, 3, 2, 14),
    (435, 3, 2, 15),
    (436, 3, 2, 16),
    (437, 3, 2, 17),
    (438, 3, 2, 18),
    (439, 3, 2, 19),
    (440, 3, 2, 20),
    (441, 3, 3, 1),
    (442, 3, 3, 2),
    (443, 3, 3, 3),
    (444, 3, 3, 4),
    (445, 3, 3, 5),
    (446, 3, 3, 6),
    (447, 3, 3, 7),
    (448, 3, 3, 8),
    (449, 3, 3, 9),
    (450, 3, 3, 10),
    (451, 3, 3, 11),
    (452, 3, 3, 12),
    (453, 3, 3, 13),
    (454, 3, 3, 14),
    (455, 3, 3, 15),
    (456, 3, 3, 16),
    (457, 3, 3, 17),
    (458, 3, 3, 18),
    (459, 3, 3, 19),
    (460, 3, 3, 20),
    (461, 3, 4, 1),
    (462, 3, 4, 2),
    (463, 3, 4, 3),
    (464, 3, 4, 4),
    (465, 3, 4, 5),
    (466, 3, 4, 6),
    (467, 3, 4, 7),
    (468, 3, 4, 8),
    (469, 3, 4, 9),
    (470, 3, 4, 10),
    (471, 3, 4, 11),
    (472, 3, 4, 12),
    (473, 3, 4, 13),
    (474, 3, 4, 14),
    (475, 3, 4, 15),
    (476, 3, 4, 16),
    (477, 3, 4, 17),
    (478, 3, 4, 18),
    (479, 3, 4, 19),
    (480, 3, 4, 20),
    (481, 3, 5, 1),
    (482, 3, 5, 2),
    (483, 3, 5, 3),
    (484, 3, 5, 4),
    (485, 3, 5, 5),
    (486, 3, 5, 6),
    (487, 3, 5, 7),
    (488, 3, 5, 8),
    (489, 3, 5, 9),
    (490, 3, 5, 10),
    (491, 3, 5, 11),
    (492, 3, 5, 12),
    (493, 3, 5, 13),
    (494, 3, 5, 14),
    (495, 3, 5, 15),
    (496, 3, 5, 16),
    (497, 3, 5, 17),
    (498, 3, 5, 18),
    (499, 3, 5, 19),
    (500, 3, 5, 20),
    (501, 3, 6, 1),
    (502, 3, 6, 2),
    (503, 3, 6, 3),
    (504, 3, 6, 4),
    (505, 3, 6, 5),
    (506, 3, 6, 6),
    (507, 3, 6, 7),
    (508, 3, 6, 8),
    (509, 3, 6, 9),
    (510, 3, 6, 10),
    (511, 3, 6, 11),
    (512, 3, 6, 12),
    (513, 3, 6, 13),
    (514, 3, 6, 14),
    (515, 3, 6, 15),
    (516, 3, 6, 16),
    (517, 3, 6, 17),
    (518, 3, 6, 18),
    (519, 3, 6, 19),
    (520, 3, 6, 20),
    (521, 3, 7, 1),
    (522, 3, 7, 2),
    (523, 3, 7, 3),
    (524, 3, 7, 4),
    (525, 3, 7, 5),
    (526, 3, 7, 6),
    (527, 3, 7, 7),
    (528, 3, 7, 8),
    (529, 3, 7, 9),
    (530, 3, 7, 10),
    (531, 3, 7, 11),
    (532, 3, 7, 12),
    (533, 3, 7, 13),
    (534, 3, 7, 14),
    (535, 3, 7, 15),
    (536, 3, 7, 16),
    (537, 3, 7, 17),
    (538, 3, 7, 18),
    (539, 3, 7, 19),
    (540, 3, 7, 20),
    (541, 3, 8, 1),
    (542, 3, 8, 2),
    (543, 3, 8, 3),
    (544, 3, 8, 4),
    (545, 3, 8, 5),
    (546, 3, 8, 6),
    (547, 3, 8, 7),
    (548, 3, 8, 8),
    (549, 3, 8, 9),
    (550, 3, 8, 10),
    (551, 3, 8, 11),
    (552, 3, 8, 12),
    (553, 3, 8, 13),
    (554, 3, 8, 14),
    (555, 3, 8, 15),
    (556, 3, 8, 16),
    (557, 3, 8, 17),
    (558, 3, 8, 18),
    (559, 3, 8, 19),
    (560, 3, 8, 20),
    (561, 3, 9, 1),
    (562, 3, 9, 2),
    (563, 3, 9, 3),
    (564, 3, 9, 4),
    (565, 3, 9, 5),
    (566, 3, 9, 6),
    (567, 3, 9, 7),
    (568, 3, 9, 8),
    (569, 3, 9, 9),
    (570, 3, 9, 10),
    (571, 3, 9, 11),
    (572, 3, 9, 12),
    (573, 3, 9, 13),
    (574, 3, 9, 14),
    (575, 3, 9, 15),
    (576, 3, 9, 16),
    (577, 3, 9, 17),
    (578, 3, 9, 18),
    (579, 3, 9, 19),
    (580, 3, 9, 20),
    (581, 3, 10, 1),
    (582, 3, 10, 2),
    (583, 3, 10, 3),
    (584, 3, 10, 4),
    (585, 3, 10, 5),
    (586, 3, 10, 6),
    (587, 3, 10, 7),
    (588, 3, 10, 8),
    (589, 3, 10, 9),
    (590, 3, 10, 10),
    (591, 3, 10, 11),
    (592, 3, 10, 12),
    (593, 3, 10, 13),
    (594, 3, 10, 14),
    (595, 3, 10, 15),
    (596, 3, 10, 16),
    (597, 3, 10, 17),
    (598, 3, 10, 18),
    (599, 3, 10, 19),
    (600, 3, 10, 20);