
USE EduSys
GO
-- so luong nguoi hoc
GO
CREATE PROC SP_LUONGNGUOIHOC 
AS BEGIN 
	SELECT 
		YEAR(NGAYDK) NAM , 
		COUNT(*) AS SOLUONG ,
		MIN (NGAYDK) DAUTIEN , 
		MAX(NGAYDK) CUOICUNG  
	FROM NGUOIHOC 
		GROUP BY YEAR (NGAYDK)
END

EXEC SP_LUONGNGUOIHOC
GO
-- SP BANG DIEM
GO
CREATE PROC SP_BANGDIEM(@MAKH INT)
AS
BEGIN
	SELECT HOCVIEN.MANH AS MA_NG_HOC , 
		HOTEN  , 
		DIEM  
	FROM HOCVIEN
		JOIN NGUOIHOC ON HOCVIEN.MANH = NGUOIHOC.MANH
		WHERE HOCVIEN.MAKH = @MAKH
	ORDER BY HOCVIEN.DIEM DESC
END
EXEC SP_BANGDIEM 1

-- THONG KE DIEM
GO
CREATE PROC SP_THONGKEDIEM 
AS BEGIN
	SELECT 
		TENCD AS CHUYENDE ,
		COUNT(MAHV) SOHV ,
		MIN(DIEM) AS THAPNHAT ,
		MAX(DIEM) AS CAONHAT,
		AVG(DIEM) AS TRUNGBINH
	FROM KHOAHOC
		JOIN HOCVIEN ON KHOAHOC.MAKH = HOCVIEN.MAKH
		JOIN CHUYENDE ON CHUYENDE.MACD = KHOAHOC.MACD
	GROUP BY TENCD
END
EXEC SP_THONGKEDIEM

-- DOANH THU
GO
CREATE PROC SP_THONGKE_DOANHTHU (@YEAR INT )
AS BEGIN
	SELECT 
		TENCD CHUYENDE ,
		COUNT(DISTINCT KHOAHOC.MAKH) SOKH,
		COUNT(HOCVIEN.MAHV) SOHV ,
		SUM(KHOAHOC.HOCPHI) DOANHTHU ,
		MIN(KHOAHOC.HOCPHI) THAPNHAT ,
		MAX (KHOAHOC.HOCPHI) CAONHAT ,
		AVG(KHOAHOC.HOCPHI) TRUNGBINH
	FROM KHOAHOC
		JOIN HOCVIEN ON KHOAHOC.MAKH = HOCVIEN.MAKH
		JOIN CHUYENDE ON CHUYENDE.MACD = KHOAHOC.MACD
	WHERE YEAR(NGAYKG) = @YEAR
	GROUP BY TENCD
END
EXEC SP_THONGKE_DOANHTHU 2023
go

select count(MAKH) from KHOAHOC where MAKH = 1 

