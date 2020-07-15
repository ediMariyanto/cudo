package com.cudocom.barang.repo;

import android.os.AsyncTask;

import com.cudocom.barang.AppExecutors;
import com.cudocom.barang.db.BarangDao;
import com.cudocom.barang.model.Barang;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class StockRepository {
    private final BarangDao barangDao;
    private final AppExecutors appExecutors;

    @Inject
    public StockRepository(BarangDao barangDao, AppExecutors appExecutors) {
        this.barangDao = barangDao;
        this.appExecutors = appExecutors;
    }

    public List<Barang> getBarangAll() {
        List<Barang> barangList = null;
        try {
            barangList = new GetBarangAllAsyncTask(barangDao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return barangList;
    }

    public int getMaxSeq() {
        int maxSeq = 0;
        try {
            maxSeq = new GetMaxSeqAsyncTask(barangDao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return maxSeq;
    }


    public void saveBarang(Barang barang) {
        try {
            new InsertBarangAsyncTask(barangDao).execute(barang).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateBarang(Barang barang) {
        try {
            new UpdateBarangAsyncTask(barangDao).execute(barang).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteBarang(Barang barang) {
        try {
            new DeleteBarangAsyncTask(barangDao).execute(barang).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class GetBarangAllAsyncTask extends AsyncTask<Void, Void, List<Barang>> {

        BarangDao barangDao;

        public GetBarangAllAsyncTask(BarangDao barangDao) {
            this.barangDao = barangDao;
        }

        @Override
        protected List<Barang> doInBackground(Void... voids) {
            return barangDao.getAll();
        }
    }

    private static class GetMaxSeqAsyncTask extends AsyncTask<Void, Void, Integer> {

        BarangDao barangDao;

        public GetMaxSeqAsyncTask(BarangDao barangDao) {
            this.barangDao = barangDao;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return barangDao.getMaxSeq();
        }
    }

    private static class InsertBarangAsyncTask extends AsyncTask<Barang, Void, Void> {

        BarangDao barangDao;

        public InsertBarangAsyncTask(BarangDao barangDao) {
            this.barangDao = barangDao;
        }


        @Override
        protected Void doInBackground(Barang... barangs) {
            barangDao.insertAll(barangs);
            return null;
        }
    }

    private static class UpdateBarangAsyncTask extends AsyncTask<Barang, Void, Void> {

        BarangDao barangDao;

        public UpdateBarangAsyncTask(BarangDao barangDao) {
            this.barangDao = barangDao;
        }


        @Override
        protected Void doInBackground(Barang... barangs) {
            barangDao.updateBarang(barangs);
            return null;
        }
    }

    private static class DeleteBarangAsyncTask extends AsyncTask<Barang, Void, Void> {

        BarangDao barangDao;

        public DeleteBarangAsyncTask(BarangDao barangDao) {
            this.barangDao = barangDao;
        }


        @Override
        protected Void doInBackground(Barang... barangs) {
            barangDao.deleteBarang(barangs);
            return null;
        }
    }

}
