INSERT INTO teams (id, name) VALUES (1, 'Argentina');
INSERT INTO teams (id, name) VALUES (2, 'Bolivia');
INSERT INTO teams (id, name) VALUES (3, 'Brazil');
INSERT INTO teams (id, name) VALUES (4, 'Chile');
INSERT INTO teams (id, name) VALUES (5, 'Canada');
INSERT INTO teams (id, name) VALUES (6, 'Colombia');
INSERT INTO teams (id, name) VALUES (7, 'Costa Rica');
INSERT INTO teams (id, name) VALUES (8, 'Ecuador');
INSERT INTO teams (id, name) VALUES (9, 'Estado Unidos');
INSERT INTO teams (id, name) VALUES (10, 'Jamaica');
INSERT INTO teams (id, name) VALUES (11, 'Mexico');
INSERT INTO teams (id, name) VALUES (12, 'Panama');
INSERT INTO teams (id, name) VALUES (13, 'Paraguay');
INSERT INTO teams (id, name) VALUES (14, 'Peru');
INSERT INTO teams (id, name) VALUES (15, 'Uruguay');
INSERT INTO teams (id, name) VALUES (16, 'Venezuela');

INSERT INTO users (id, name, last_name, email, password) VALUES (100, 'Hernan', 'Morais', 'hjm@hjm.com', 'password');
INSERT INTO users (id, name, last_name, email, password) VALUES (200, 'Luca', 'Morais', 'lm@hjm.com', 'password');
INSERT INTO users (id, name, last_name, email, password) VALUES (300, 'Sebastian', 'Morais', 'sm@hjm.com', 'password');
INSERT INTO users (id, name, last_name, email, password) VALUES (400, 'Damian', 'Morais', 'dam@hjm.com', 'password');

INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (1, 1, 5, 'Mercedes Benz Stadium - Atlanta, GA', '2024-06-20 20:00:00', 'group_stage', 'A');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (2, 14, 4, 'AT&T Stadium - Arlington, TX', '2024-06-21 19:00:00', 'group_stage', 'A');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (3, 11, 10, 'NRG Stadium - Houston, TX', '2024-06-22 20:00:00', 'group_stage', 'B');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (4, 8, 16, 'Levis Stadium - Santa Clara, CA', '2024-06-22 15:00:00', 'group_stage', 'B');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (5, 9, 2, 'AT&T Stadium - Arlington, TX', '2024-06-23 17:00:00', 'group_stage', 'C');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (6, 15, 12, 'Hard Rock Stadium - Miami, FL', '2024-06-23 21:00:00', 'group_stage', 'C');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (7, 3, 7, 'SoFi Stadium - Inglewood, CA', '2024-06-24 18:00:00', 'group_stage', 'D');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (8, 6, 13, 'NRG Stadium - Houston, TX', '2024-06-24 17:00:00', 'group_stage', 'D');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (9, 4, 1, 'MetLife Stadium - East Rutherford, NJ', '2024-06-25 21:00:00', 'group_stage', 'A');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (10, 14, 5, 'Children''s Mercy Park - Kansas City, KS', '2024-06-25 17:00:00', 'group_stage', 'A');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (11, 16, 11, 'SoFi Stadium - Inglewood, CA', '2024-06-26 18:00:00', 'group_stage', 'B');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (12, 8, 10, 'Allegiant Stadium - Las Vegas, NV', '2024-06-26 15:00:00', 'group_stage', 'B');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (13, 12, 9, 'Mercedes Benz Stadium - Atlanta, GA', '2024-06-27 18:00:00', 'group_stage', 'C');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (14, 15, 2, 'MetLife Stadium - East Rutherford, NJ', '2024-06-27 21:00:00', 'group_stage', 'C');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (15, 13, 3, 'Allegiant Stadium - Las Vegas, NV', '2024-06-28 18:00:00', 'group_stage', 'D');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (16, 6, 7, 'State Farm Stadium - Glendale, AZ', '2024-06-28 15:00:00', 'group_stage', 'D');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (17, 1, 14, 'Hard Rock Stadium - Miami, FL', '2024-06-29 20:00:00', 'group_stage', 'A');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (18, 5, 4, 'Inter&Co Stadium - Orlando, FL', '2024-06-29 20:00:00', 'group_stage', 'A');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (19, 11, 8, 'State Farm Stadium - Glendale, AZ', '2024-06-30 17:00:00', 'group_stage', 'B');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (20, 10, 16, 'Q2 Stadium - Austin, TX', '2024-06-30 19:00:00', 'group_stage', 'B');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (21, 9, 15, 'GEHA Field at Arrowhead - Kansas City, MO', '2024-07-01 20:00:00', 'group_stage', 'C');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (22, 2, 12, 'Inter&Co Stadium - Orlando, FL', '2024-07-01 21:00:00', 'group_stage', 'C');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (23, 3, 6, 'Levis Stadium - Santa Clara, CA', '2024-07-02 18:00:00', 'group_stage', 'D');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (24, 7, 13, 'Q2 Stadium - Austin, TX', '2024-07-02 20:00:00', 'group_stage', 'D');
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (25, null, null, 'NRG Stadium - Houston, TX', '2024-07-04 20:00:00', 'QUARTER_FINALS', null);
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (26, null, null, 'AT&T Stadium - Arlington, TX', '2024-07-05 20:00:00', 'QUARTER_FINALS', null);
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (27, null, null, 'Allegiant Stadium - Las Vegas, NV', '2024-07-06 20:00:00', 'QUARTER_FINALS', null);
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (28, null, null, 'State Farm Stadium - Glendale, AZ', '2024-07-06 15:00:00', 'QUARTER_FINALS', null);
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (29, null, null, 'MetLife Stadium - East Rutherford, NJ', '2024-07-09 20:00:00', 'SEMI_FINALS', null);
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (30, null, null, 'Bank of America Stadium - Charlotte, NC', '2024-07-10 20:00:00', 'SEMI_FINALS', null);
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (31, null, null, 'Bank of America Stadium - Charlotte, NC', '2024-07-13 20:00:00', 'THIRD_PLACE', null);
INSERT INTO games (id, local_team_id, visitor_team_id, stadium, game_date, fase_game, group_stage)
VALUES (32, null, null, 'Hard Rock Stadium - Miami, FL', '2024-07-14 20:00:00', 'FINAL', null);

INSERT INTO predictions (user_id, game_id, local_goals, visitor_goals, prediction_date, result)
VALUES (100, 1, 2, 0, '2024-06-20 19:00:00', 'LOCAL_WIN');
INSERT INTO predictions (user_id, game_id, local_goals, visitor_goals, prediction_date, result)
VALUES (200, 1, 2, 1, '2024-06-20 19:00:00', 'LOCAL_WIN');
INSERT INTO predictions (user_id, game_id, local_goals, visitor_goals, prediction_date, result)
VALUES (300, 1, 2, 0, '2024-06-20 19:00:00', 'LOCAL_WIN');
INSERT INTO predictions (user_id, game_id, local_goals, visitor_goals, prediction_date, result)
VALUES (400, 1, 0, 0, '2024-06-20 19:00:00', 'TIE');

INSERT INTO game_results (id, game_id, local_goals, visitor_goals, result)
VALUES (1, 1, 2, 0, 'LOCAL_WIN');