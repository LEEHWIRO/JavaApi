SELECT idx.relname                               AS index_name,
       tbl.relname                               AS table_name,
       pg_size_pretty(pg_relation_size(idx.oid)) AS index_size
FROM pg_class idx
         JOIN
     pg_index i ON idx.oid = i.indexrelid
         JOIN
     pg_class tbl ON i.indrelid = tbl.oid
         JOIN
     pg_namespace nsp ON tbl.relnamespace = nsp.oid
WHERE idx.relkind = 'i'
  AND nsp.nspname = 'nsop'
  and tbl.relname in (
                      'tb_p100h01', 'tb_p100m02', 'tb_p100n01', 'tb_p101h01', 'tb_p101h02', 'tb_p101h03', 'tb_p101m01',
                      'tb_p101m02', 'tb_p101m03', 'tb_p102h01', 'tb_p102h02', 'tb_p102h03', 'tb_p102h04', 'tb_p102h05',
                      'tb_p102m01', 'tb_p102n01', 'tb_p103h01', 'tb_p103m01', 'tb_p103n01', 'tb_p104h01', 'tb_p104h02',
                      'tb_p104h03', 'tb_p104h04', 'tb_p104h05', 'tb_p104m01', 'tb_p105h01', 'tb_p105h02', 'tb_p105m01',
                      'tb_p105t01', 'tb_p106h01', 'tb_p106m01', 'tb_p107h01', 'tb_p107h02', 'tb_p107h03', 'tb_p107m01',
                      'tb_p107n01', 'tb_p107n02', 'tb_p107n03', 'tb_p108h01', 'tb_p108m01', 'tb_p109h01', 'tb_p109m01',
                      'tb_p109n01', 'tb_p110h01', 'tb_p110m01', 'tb_p111h01', 'tb_p111h02', 'tb_p111m01', 'tb_p112h01',
                      'tb_p112h02', 'tb_p112m01', 'tb_p113h01', 'tb_p113h02', 'tb_p113h03', 'tb_p113m01', 'tb_p113n01',
                      'tb_p190m01', 'tb_p191m01', 'tb_r101c01', 'tb_r101c02', 'tb_r101c03', 'tb_r101c04', 'tb_r102c01',
                      'tb_r102c02', 'tb_r102c03', 'tb_r102c04', 'tb_r102c05', 'tb_r103c01', 'tb_r110c01', 'tb_r111n01',
                      'tb_r201m01', 'tb_r201m02', 'tb_r201m03', 'tb_r201m04', 'tb_r201m05', 'tb_r201n01', 'tb_r202m01',
                      'tb_r202m02', 'tb_r203m01', 'tb_r203m02', 'tb_r203m03', 'tb_r203m04', 'tb_r203m05', 'tb_r203m06',
                      'tb_r203m07', 'tb_r203m08', 'tb_r203m10', 'tb_r203m11', 'tb_r203m12', 'tb_r203m13', 'tb_r204m01',
                      'tb_r204m02', 'tb_r204m03', 'tb_r204m04', 'tb_r204m05', 'tb_r204m06', 'tb_r205m01', 'tb_r206m01',
                      'tb_r901h01', 'tb_r902h01', 'tb_r910s01', 'tb_r920s01', 'tb_p201_d01', 'tb_p201_d02',
                      'tb_p201_d03', 'tb_p201_d04', 'tb_p201_d05', 'tb_p201_d06', 'tb_p201_d07', 'tb_p201_d08',
                      'tb_p201_d09', 'tb_p201_m01', 'tb_p201_m02', 'tb_p201_n01', 'tb_p201_n02', 'tb_p201_n03',
                      'tb_p201_n04', 'tb_p201_n05', 'tb_p202_m01', 'tb_p203_m01', 'tb_p203_n01', 'tb_p204_m01',
                      'tb_p204_n01', 'tb_p205_h01', 'tb_p205_m01', 'tb_p207_m01', 'tb_p207_n01', 'tb_p208_m01',
                      'tb_p209_m01', 'tb_p209_m02', 'tb_p211_m01', 'tb_p211_n01', 'tb_p212_d01', 'tb_p212_d02',
                      'tb_p212_d03', 'tb_p212_d04', 'tb_p212_d05', 'tb_p212_d06', 'tb_p212_m01', 'tb_p212_n01',
                      'tb_p213_m01', 'tb_p213_m02', 'tb_r207d01', 'tb_r207d02', 'tb_r207m01', 'tb_r207m02',
                      'tb_r920s02', 'tb_p101m03', 'tb_p111m02', 'tb_p202_m02', 'tb_p214_m02', 'tb_p215_m02'
    )
ORDER BY CASE
             WHEN tbl.relname = 'tb_p100h01' THEN 1
             WHEN tbl.relname = 'tb_p100m02' THEN 2
             WHEN tbl.relname = 'tb_p100n01' THEN 3
             WHEN tbl.relname = 'tb_p101h01' THEN 4
             WHEN tbl.relname = 'tb_p101h02' THEN 5
             WHEN tbl.relname = 'tb_p101h03' THEN 6
             WHEN tbl.relname = 'tb_p101m01' THEN 7
             WHEN tbl.relname = 'tb_p101m02' THEN 8
             WHEN tbl.relname = 'tb_p101m03' THEN 9
             WHEN tbl.relname = 'tb_p102h01' THEN 10
             WHEN tbl.relname = 'tb_p102h02' THEN 11
             WHEN tbl.relname = 'tb_p102h03' THEN 12
             WHEN tbl.relname = 'tb_p102h04' THEN 13
             WHEN tbl.relname = 'tb_p102h05' THEN 14
             WHEN tbl.relname = 'tb_p102m01' THEN 15
             WHEN tbl.relname = 'tb_p102n01' THEN 16
             WHEN tbl.relname = 'tb_p103h01' THEN 17
             WHEN tbl.relname = 'tb_p103m01' THEN 18
             WHEN tbl.relname = 'tb_p103n01' THEN 19
             WHEN tbl.relname = 'tb_p104h01' THEN 20
             WHEN tbl.relname = 'tb_p104h02' THEN 21
             WHEN tbl.relname = 'tb_p104h03' THEN 22
             WHEN tbl.relname = 'tb_p104h04' THEN 23
             WHEN tbl.relname = 'tb_p104h05' THEN 24
             WHEN tbl.relname = 'tb_p104m01' THEN 25
             WHEN tbl.relname = 'tb_p105h01' THEN 26
             WHEN tbl.relname = 'tb_p105h02' THEN 27
             WHEN tbl.relname = 'tb_p105m01' THEN 28
             WHEN tbl.relname = 'tb_p105t01' THEN 29
             WHEN tbl.relname = 'tb_p106h01' THEN 30
             WHEN tbl.relname = 'tb_p106m01' THEN 31
             WHEN tbl.relname = 'tb_p107h01' THEN 32
             WHEN tbl.relname = 'tb_p107h02' THEN 33
             WHEN tbl.relname = 'tb_p107h03' THEN 34
             WHEN tbl.relname = 'tb_p107m01' THEN 35
             WHEN tbl.relname = 'tb_p107n01' THEN 36
             WHEN tbl.relname = 'tb_p107n02' THEN 37
             WHEN tbl.relname = 'tb_p107n03' THEN 38
             WHEN tbl.relname = 'tb_p108h01' THEN 39
             WHEN tbl.relname = 'tb_p108m01' THEN 40
             WHEN tbl.relname = 'tb_p109h01' THEN 41
             WHEN tbl.relname = 'tb_p109m01' THEN 42
             WHEN tbl.relname = 'tb_p109n01' THEN 43
             WHEN tbl.relname = 'tb_p110h01' THEN 44
             WHEN tbl.relname = 'tb_p110m01' THEN 45
             WHEN tbl.relname = 'tb_p111h01' THEN 46
             WHEN tbl.relname = 'tb_p111h02' THEN 47
             WHEN tbl.relname = 'tb_p111m01' THEN 48
             WHEN tbl.relname = 'tb_p112h01' THEN 49
             WHEN tbl.relname = 'tb_p112h02' THEN 50
             WHEN tbl.relname = 'tb_p112m01' THEN 51
             WHEN tbl.relname = 'tb_p113h01' THEN 52
             WHEN tbl.relname = 'tb_p113h02' THEN 53
             WHEN tbl.relname = 'tb_p113h03' THEN 54
             WHEN tbl.relname = 'tb_p113m01' THEN 55
             WHEN tbl.relname = 'tb_p113n01' THEN 56
             WHEN tbl.relname = 'tb_p190m01' THEN 57
             WHEN tbl.relname = 'tb_p191m01' THEN 58
             WHEN tbl.relname = 'tb_r101c01' THEN 59
             WHEN tbl.relname = 'tb_r101c02' THEN 60
             WHEN tbl.relname = 'tb_r101c03' THEN 61
             WHEN tbl.relname = 'tb_r101c04' THEN 62
             WHEN tbl.relname = 'tb_r102c01' THEN 63
             WHEN tbl.relname = 'tb_r102c02' THEN 64
             WHEN tbl.relname = 'tb_r102c03' THEN 65
             WHEN tbl.relname = 'tb_r102c04' THEN 66
             WHEN tbl.relname = 'tb_r102c05' THEN 67
             WHEN tbl.relname = 'tb_r103c01' THEN 68
             WHEN tbl.relname = 'tb_r110c01' THEN 69
             WHEN tbl.relname = 'tb_r111n01' THEN 70
             WHEN tbl.relname = 'tb_r201m01' THEN 71
             WHEN tbl.relname = 'tb_r201m02' THEN 72
             WHEN tbl.relname = 'tb_r201m03' THEN 73
             WHEN tbl.relname = 'tb_r201m04' THEN 74
             WHEN tbl.relname = 'tb_r201m05' THEN 75
             WHEN tbl.relname = 'tb_r201n01' THEN 76
             WHEN tbl.relname = 'tb_r202m01' THEN 77
             WHEN tbl.relname = 'tb_r202m02' THEN 78
             WHEN tbl.relname = 'tb_r203m01' THEN 79
             WHEN tbl.relname = 'tb_r203m02' THEN 80
             WHEN tbl.relname = 'tb_r203m03' THEN 81
             WHEN tbl.relname = 'tb_r203m04' THEN 82
             WHEN tbl.relname = 'tb_r203m05' THEN 83
             WHEN tbl.relname = 'tb_r203m06' THEN 84
             WHEN tbl.relname = 'tb_r203m07' THEN 85
             WHEN tbl.relname = 'tb_r203m08' THEN 86
             WHEN tbl.relname = 'tb_r203m10' THEN 87
             WHEN tbl.relname = 'tb_r203m11' THEN 88
             WHEN tbl.relname = 'tb_r203m12' THEN 89
             WHEN tbl.relname = 'tb_r203m13' THEN 90
             WHEN tbl.relname = 'tb_r204m01' THEN 91
             WHEN tbl.relname = 'tb_r204m02' THEN 92
             WHEN tbl.relname = 'tb_r204m03' THEN 93
             WHEN tbl.relname = 'tb_r204m04' THEN 94
             WHEN tbl.relname = 'tb_r204m05' THEN 95
             WHEN tbl.relname = 'tb_r204m06' THEN 96
             WHEN tbl.relname = 'tb_r205m01' THEN 97
             WHEN tbl.relname = 'tb_r206m01' THEN 98
             WHEN tbl.relname = 'tb_r901h01' THEN 99
             WHEN tbl.relname = 'tb_r902h01' THEN 100
             WHEN tbl.relname = 'tb_r910s01' THEN 101
             WHEN tbl.relname = 'tb_r920s01' THEN 102
             WHEN tbl.relname = 'tb_p201_d01' THEN 103
             WHEN tbl.relname = 'tb_p201_d02' THEN 104
             WHEN tbl.relname = 'tb_p201_d03' THEN 105
             WHEN tbl.relname = 'tb_p201_d04' THEN 106
             WHEN tbl.relname = 'tb_p201_d05' THEN 107
             WHEN tbl.relname = 'tb_p201_d06' THEN 108
             WHEN tbl.relname = 'tb_p201_d07' THEN 109
             WHEN tbl.relname = 'tb_p201_d08' THEN 110
             WHEN tbl.relname = 'tb_p201_d09' THEN 111
             WHEN tbl.relname = 'tb_p201_m01' THEN 112
             WHEN tbl.relname = 'tb_p201_m02' THEN 113
             WHEN tbl.relname = 'tb_p201_n01' THEN 114
             WHEN tbl.relname = 'tb_p201_n02' THEN 115
             WHEN tbl.relname = 'tb_p201_n03' THEN 116
             WHEN tbl.relname = 'tb_p201_n04' THEN 117
             WHEN tbl.relname = 'tb_p201_n05' THEN 118
             WHEN tbl.relname = 'tb_p202_m01' THEN 119
             WHEN tbl.relname = 'tb_p203_m01' THEN 120
             WHEN tbl.relname = 'tb_p203_n01' THEN 121
             WHEN tbl.relname = 'tb_p204_m01' THEN 122
             WHEN tbl.relname = 'tb_p204_n01' THEN 123
             WHEN tbl.relname = 'tb_p205_h01' THEN 124
             WHEN tbl.relname = 'tb_p205_m01' THEN 125
             WHEN tbl.relname = 'tb_p207_m01' THEN 126
             WHEN tbl.relname = 'tb_p207_n01' THEN 127
             WHEN tbl.relname = 'tb_p208_m01' THEN 128
             WHEN tbl.relname = 'tb_p209_m01' THEN 129
             WHEN tbl.relname = 'tb_p209_m02' THEN 130
             WHEN tbl.relname = 'tb_p211_m01' THEN 131
             WHEN tbl.relname = 'tb_p211_n01' THEN 132
             WHEN tbl.relname = 'tb_p212_d01' THEN 133
             WHEN tbl.relname = 'tb_p212_d02' THEN 134
             WHEN tbl.relname = 'tb_p212_d03' THEN 135
             WHEN tbl.relname = 'tb_p212_d04' THEN 136
             WHEN tbl.relname = 'tb_p212_d05' THEN 137
             WHEN tbl.relname = 'tb_p212_d06' THEN 138
             WHEN tbl.relname = 'tb_p212_m01' THEN 139
             WHEN tbl.relname = 'tb_p212_n01' THEN 140
             WHEN tbl.relname = 'tb_p213_m01' THEN 141
             WHEN tbl.relname = 'tb_p213_m02' THEN 142
             WHEN tbl.relname = 'tb_r207d01' THEN 143
             WHEN tbl.relname = 'tb_r207d02' THEN 144
             WHEN tbl.relname = 'tb_r207m01' THEN 145
             WHEN tbl.relname = 'tb_r207m02' THEN 146
             WHEN tbl.relname = 'tb_r920s02' THEN 147
             WHEN tbl.relname = 'tb_p101m03' THEN 148
             WHEN tbl.relname = 'tb_p111m02' THEN 149
             WHEN tbl.relname = 'tb_p202_m02' THEN 150
             WHEN tbl.relname = 'tb_p214_m02' THEN 151
             WHEN tbl.relname = 'tb_p215_m02' THEN 152
             END;



select LNMADR,
       RDNMADR,
       COALESCE(LNMADR, RDNMADR) AS FCTLS_ADRES,
       case
           when COALESCE(LNMADR, RDNMADR) = LNMADR then 'parcel'
           else 'road' end            as ADDRESS_TYPE
       CHG_STTUS
from tb_p191m01;