using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Bike.Models;

namespace Bike.Controllers
{
    public class BikeData1Controller : Controller
    {
        private BikeInfoEntities db = new BikeInfoEntities();

        // GET: BikeData1
        public ActionResult Index()
        {
            var bikeData1 = db.BikeData1.Include(b => b.Category);
            return View(bikeData1.ToList());
        }

        // GET: BikeData1/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            BikeData1 bikeData1 = db.BikeData1.Find(id);
            if (bikeData1 == null)
            {
                return HttpNotFound();
            }
            return View(bikeData1);
        }

        // GET: BikeData1/Create
        public ActionResult Create()
        {
            ViewBag.CategoryName = new SelectList(db.Categories, "CategoryName", "CategoryName");
            return View();
        }

        // POST: BikeData1/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "BikeId,BikeName,Color,MaxPower,EngineType,MaxSpeed,Aversge,Price,CategoryName")] BikeData1 bikeData1)
        {
            if (ModelState.IsValid)
            {
                db.BikeData1.Add(bikeData1);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.CategoryName = new SelectList(db.Categories, "CategoryName", "CategoryName", bikeData1.CategoryName);
            return View(bikeData1);
        }

        // GET: BikeData1/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            BikeData1 bikeData1 = db.BikeData1.Find(id);
            if (bikeData1 == null)
            {
                return HttpNotFound();
            }
            ViewBag.CategoryName = new SelectList(db.Categories, "CategoryName", "CategoryName", bikeData1.CategoryName);
            return View(bikeData1);
        }

        // POST: BikeData1/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "BikeId,BikeName,Color,MaxPower,EngineType,MaxSpeed,Aversge,Price,CategoryName")] BikeData1 bikeData1)
        {
            if (ModelState.IsValid)
            {
                db.Entry(bikeData1).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.CategoryName = new SelectList(db.Categories, "CategoryName", "CategoryName", bikeData1.CategoryName);
            return View(bikeData1);
        }

        // GET: BikeData1/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            BikeData1 bikeData1 = db.BikeData1.Find(id);
            if (bikeData1 == null)
            {
                return HttpNotFound();
            }
            return View(bikeData1);
        }

        // POST: BikeData1/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            BikeData1 bikeData1 = db.BikeData1.Find(id);
            db.BikeData1.Remove(bikeData1);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
