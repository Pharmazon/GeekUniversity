-- 1 ------------------------------------------------
-- ошибки в расписании (фильмы накладываются друг на друга),
-- отсортированные по возрастанию времени. Выводить надо колонки «фильм 1»,
-- «время начала», «длительность», «фильм 2», «время начала», «длительность»;
-----------------------------------------------------
select *
from dt_view one,
     dt_view two
where (one.begin_time, make_interval(mins => one.duration))
    overlaps (two.begin_time, make_interval(mins => two.duration))
  and one.begin_time <> two.begin_time
  and two.begin_time > one.begin_time;

-- 2 ------------------------------------------------
-- перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1»,
-- «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
-----------------------------------------------------
-- Не придумал, как оставить только одну запись в таблице 2 (movie2, begin_time2, duration2) с минимальным break
-- Сейчас таблица отражает перерывы до конца дня от каждого фильма
select *
from (
    select one.movie as movie1,
           one.begin_time as begin_time1,
           one.duration as duration1,
           two.movie as movie2,
           two.begin_time as begin_time2,
           two.duration as duration2,
           extract(epoch from two.begin_time - (one.begin_time + make_interval(mins => one.duration))) / 60 as break
    from dt_view one,
       dt_view two
    where one.begin_time <> two.begin_time
    order by one.movie, one.begin_time asc) tab
where break >= 30;

-- 3 ------------------------------------------------
-- список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за
-- сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть
-- строчка «итого», содержащая данные по всем фильмам сразу;
-----------------------------------------------------
select *
from stats_view
union all
select 'ИТОГО',
       sum(visitors),
       round(avg(avrg), 2),
       sum(sold)
from stats_view;

-- 4 ------------------------------------------------
-- число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21,
-- с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
-----------------------------------------------------
-- Добавил дату, чтобы универсиализировать
create type visit as (time_range varchar(17), visitors bigint, revenue money);

create or replace function get_rows_by_time_range(timestamp, timestamp) returns setof visit as
$$
select ($1 || ' - ' || $2) as time_range,
       count(ticket_num)   as visitors,
       sum(price)          as revenue
from timetable t
         left join orders o on t.id = o.timetable_id
where begin_time >= $1
  and begin_time < $2
group by time_range;
$$ language sql;

select * from get_rows_by_time_range('2019-09-14 09:00:00'::timestamp, '2019-09-14 15:00:00'::timestamp)
union all
select * from get_rows_by_time_range('2019-09-14 15:00:00'::timestamp, '2019-09-14 18:00:00'::timestamp)
union all
select * from get_rows_by_time_range('2019-09-14 18:00:00'::timestamp, '2019-09-14 21:00:00'::timestamp)
union all
select * from get_rows_by_time_range('2019-09-14 21:00:00'::timestamp, '2019-09-14 00:00:00'::timestamp);

