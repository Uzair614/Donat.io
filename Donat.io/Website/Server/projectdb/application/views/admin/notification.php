
	        <div class="content">
	            <div class="container-fluid">
	                <div class="row">
	                    <div class="col-md-12">
	                        <div class="card">
	                            <div class="card-header" data-background-color="purple">
	                                <h4 class="title">Send Notifications</h4>
	                            </div>
	                            <div class="card-content">
	                                <form method="post">
	                                    <div class="row">
	                                        <div class="col-md-12">
																						<div class="form-group">
																							<textarea class="form-control"
																								placeholder="Insert Notifications here." rows="5" id="Message" required>
																							</textarea>
																						</div>
	                                        </div>
																				</div>

																				<div class="radio">
																					<label>
																						<input type="radio" name="optionsRadios" checked="true" id="VolunteerRadio">
																						Send to your Volunteers
																					</label>
																				</div>
																				<div class="radio">
																					<label>
																						<input type="radio" name="optionsRadios" id="UserRadio">
																						Send to All Users
																					</label>
																				</div>

                                    <button name = "sendButon" class="btn btn-primary pull-right" id="SendNotification">Send Notification</button>
                                    <div class="clearfix"></div>
	                                </form>
	                            </div>
	                        </div>
	                    </div>

											<div class="col-md-12">
	                        <div class="card">
	                            <div class="card-header" data-background-color="purple">
	                                <h4 class="title">Your previous Notifications</h4>
	                            </div>
	                            <div class="card-content table-responsive">
																<table class="table">
																		<thead class="text-primary">
																			<th>Message</th>
																			<th>To</th>
																			<th>Date</th>
																			<th>Options</th>
																		</thead>
																		<tbody>
																			<?php foreach($notificationsList as $notification): ?>
																				<tr>
																					<td><?= $notification->getMessage()?></td>
																					<td><?= $notification->getAudience()?></td>
																					<td><?= $notification->getDate()?></td>
																					<td>
																						<button type="button" name = "deleteButton" class="btn btn-danger" id="<?= $notification->getmID()?>">Delete</button>
																					</td>
																				</tr>
																			<?php endforeach; ?>
																		</tbody>
																</table>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
				</div>
