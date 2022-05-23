SELECT nombre,cedula,gastosHotel,estadia
FROM USUARIO
WHERE tipoUsuario = 3 
    AND estadia >= 15
    AND gastosHotel > 15000000;

SELECT tabs.TABLE_NAME AS NombreTabla, tabs.COLUMN_NAME AS NombreColumna, tabs.DATA_TYPE AS TipoDeDato,
       DECODE(re.CONSTRAINT_NAME, null, 'NO TIENE',re.CONSTRAINT_NAME) AS NombreRestriccion 
FROM (SELECT *
    FROM ALL_TAB_COLUMNS
    WHERE owner = 'ISIS2304A08202210') tabs
    LEFT OUTER JOIN
    (SELECT *
    FROM ALL_CONS_COLUMNS
    WHERE owner = 'ISIS2304A08202210') re
    ON tabs.COLUMN_NAME = re.COLUMN_NAME
    AND tabs.TABLE_NAME = re.TABLE_NAME
ORDER BY tabs.TABLE_NAME ASC, tabs.COLUMN_NAME ASC,  re.CONSTRAINT_NAME ASC;
