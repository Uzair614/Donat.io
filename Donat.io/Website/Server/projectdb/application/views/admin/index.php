
	        <div class="content">
	            <div class="container-fluid">
	                <div class="row">
	                    <div class="col-md-12">
	                        <div class="card">
	                            <div class="card-header" data-background-color="purple">
	                                <h4 class="title">Your Profile</h4>
	                            </div>
	                            <div class="card-content">
	                                <form>
	                                    <div class="row">

	                                        <div class="col-md-12">
																						<div class="form-group label-floating">
																							<label class="control-label">
																								Username</label>
																							<input type="text" class="form-control" disabled value="<?=$adminobj->getUsername()?>" >
																						</div>
	                                        </div>
																				</div>
																				<div class="row">
	                                        <div class="col-md-12">
																						<div class="form-group label-floating">
																							<label class="control-label">
																								Email Address</label>
																							<input type="email" class="form-control" disabled>
																						</div>
	                                        </div>
	                                    </div>

																			<div class="row">
																				<div class="col-md-12">
																					<div class="form-group label-floating">
																						<label class="control-label">
																							Organization Name</label>
																						<input type="email" class="form-control" disabled value="<?=$adminobj->getOrgName()?>" >
																					</div>
																				</div>
																			</div>

																			<div class="row">
																				<div class="col-md-12">
																					<div class="form-group label-floating">
																						<label class="control-label">
																							Registration Number</label>
																						<input type="email" class="form-control" disabled value="<?=$adminobj->getRegNo()?>"  >
																					</div>
																				</div>
																		</div>

                                    <div class="clearfix"></div>
	                                </form>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
				</div>
