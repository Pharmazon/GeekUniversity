-- Для заданий 1 и 2 ----------------------------------
create view dt_view as (
    select m.name       as movie,
           t.begin_time as begin_time,
           m.duration   as duration
    from movies m
             left join timetable t on m.id = t.movie_id
    order by begin_time asc);

-- Для задания 3 ---------------------------------------
create view stats_view as (
    select m.name                                                       as movie,
           count(o.ticket_num)                                          as visitors,
           round(count(o.ticket_num) / count(t.begin_time)::numeric, 2) as avrg,
           sum(t.price)                                                 as sold
    from movies m
             left join timetable t on m.id = t.movie_id
             left join orders o on o.timetable_id = t.id
    group by movie
    order by sold desc
);
